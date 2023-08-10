using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace CertificateEncryptionProj.Data.Migrations
{
    /// <inheritdoc />
    public partial class AddEditProperties : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<bool>(
                name: "IsEditing",
                table: "ToDoItems",
                type: "bit",
                nullable: false,
                defaultValue: false);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "IsEditing",
                table: "ToDoItems");
        }
    }
}
