using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using CertificateEncryptionProj.Models;

namespace CertificateEncryptionProj.Data
{
    public class ApplicationDbContext : IdentityDbContext
    {
        // Constructor to initialize the database context
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }
        // Property to access the ToDoItems table
        public DbSet<ToDoItems> ToDoItems { get; set; }
    }
}