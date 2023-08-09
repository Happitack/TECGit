using CertificateEncryptionProj.Areas.Identity;
using CertificateEncryptionProj.Data;
using CertificateEncryptionProj.Repo;
using CertificateEncryptionProj.Security;
using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Authorization;
using Microsoft.AspNetCore.Components.Web;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.UI;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Options;
using System.Security.Cryptography.X509Certificates;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
var connectionString = builder.Configuration.GetConnectionString("DefaultConnection") ?? throw new InvalidOperationException("Connection string 'DefaultConnection' not found.");
builder.Services.AddDbContext<ApplicationDbContext>(options =>
    options.UseSqlServer(connectionString));
builder.Services.AddDatabaseDeveloperPageExceptionFilter();
builder.Services.AddDefaultIdentity<IdentityUser>(options => options.SignIn.RequireConfirmedAccount = true)
    .AddEntityFrameworkStores<ApplicationDbContext>();
builder.Services.AddRazorPages();
builder.Services.AddServerSideBlazor();
builder.Services.AddScoped<AuthenticationStateProvider, RevalidatingIdentityAuthenticationStateProvider<IdentityUser>>();
builder.Services.AddScoped<TodoRepository>();
builder.Services.AddSingleton<WeatherForecastService>();
builder.Services.Configure<EncryptionSettings>(builder.Configuration.GetSection("Encryption"));

var encryptionSettings = new EncryptionSettings
{
    Key = builder.Configuration["Encryption:Key"] ?? throw new InvalidOperationException("Encryption:Key must be defined in appsettings.json"),
    IV = builder.Configuration["Encryption:IV"] ?? throw new InvalidOperationException("Encryption:IV must be defined in appsettings.json"),
};

builder.Services.AddSingleton(encryptionSettings);
builder.Services.AddTransient<EncryptionService>(sp =>
{
    var settings = sp.GetRequiredService<IOptions<EncryptionSettings>>().Value;
    return new EncryptionService(settings.Key, settings.IV);
});

builder.WebHost.ConfigureKestrel(options =>
{
    options.ConfigureHttpsDefaults(options =>
    {
        // Grab the secret value from the secret store.
        string? secretValue = builder.Configuration.GetValue<string>("KestrelCertificatePassword");
        options.ServerCertificate = new X509Certificate2("clu-tec.pfx", secretValue);
    });
});

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseMigrationsEndPoint();
}
else
{
    app.UseExceptionHandler("/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();

app.UseStaticFiles();

app.UseRouting();

app.UseAuthentication();
app.UseAuthorization();

app.MapControllers();
app.MapBlazorHub();
app.MapFallbackToPage("/_Host");

app.Run();
