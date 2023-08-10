using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace CertificateEncryptionProj.Data.Migrations
{
    /// <inheritdoc />
    public partial class AddHashProperties : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "DescriptionHash",
                table: "ToDoItems",
                type: "int",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<int>(
                name: "TitleHash",
                table: "ToDoItems",
                type: "int",
                nullable: false,
                defaultValue: 0);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "DescriptionHash",
                table: "ToDoItems");

            migrationBuilder.DropColumn(
                name: "TitleHash",
                table: "ToDoItems");
        }
    }
}
