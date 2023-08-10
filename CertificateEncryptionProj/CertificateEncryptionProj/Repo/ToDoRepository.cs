using CertificateEncryptionProj.Data;
using CertificateEncryptionProj.Models;
using CertificateEncryptionProj.Security;
using Microsoft.EntityFrameworkCore;
using Murmur;
using System.Text;

namespace CertificateEncryptionProj.Repo
{
    public class TodoRepository
    {
        private readonly ApplicationDbContext _context;
        private readonly EncryptionService _encryptionService;

        // Constructor to initialize the database context and encryption service
        public TodoRepository(ApplicationDbContext context, EncryptionService encryptionService)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));
            _encryptionService = encryptionService ?? throw new ArgumentNullException(nameof(encryptionService));
        }

        // Method to create a new ToDoItem
        public async Task<ToDoItems> CreateTodoItemAsync(ToDoItems item)
        {
            // Creates a hash object
            var murmur = MurmurHash.Create32();

            // Hash the title and description
            item.TitleHash = BitConverter.ToInt32(murmur.ComputeHash(Encoding.UTF8.GetBytes(item.Title)), 0);
            item.DescriptionHash = BitConverter.ToInt32(murmur.ComputeHash(Encoding.UTF8.GetBytes(item.Description)), 0);

            // Encrypt the title and description
            item.EncryptedTitle = _encryptionService.Encrypt(item.Title);
            item.EncryptedDescription = _encryptionService.Encrypt(item.Description);

            // Add the item to the database context
            _context.ToDoItems.Add(item);

            // Save the changes to the database
            await _context.SaveChangesAsync();

            // Return the newly created item
            return item;
        }

        // Method to get all ToDoItems for a specific user
        public async Task<IEnumerable<ToDoItems>> GetTodoItemsAsync(string userId)
        {
            var items = await _context.ToDoItems.Where(x => x.UserId == userId).ToListAsync();
            foreach (var item in items)
            {
                // Decrypt the title and description
                item.Title = _encryptionService.Decrypt(item.EncryptedTitle);
                item.Description = _encryptionService.Decrypt(item.EncryptedDescription);
            }
            return items;
        }

        // Method to get a single ToDoItem
        public async Task<ToDoItems> GetTodoItemAsync(int id)
        {
            var item = await _context.ToDoItems.FindAsync(id);
            if (item != null)
            {
                // Decrypt the title and description
                item.Title = _encryptionService.Decrypt(item.EncryptedTitle);
                item.Description = _encryptionService.Decrypt(item.EncryptedDescription);
            }
            return item;
        }

        // Method to update a ToDoItem
        public async Task UpdateTodoItemAsync(ToDoItems item)
        {
            var existingItem = await _context.ToDoItems.FindAsync(item.Id);

            // Create a hash object
            var murmur = MurmurHash.Create32();

            // Calculate the new hashes for title and description
            int newTitleHash = BitConverter.ToInt32(murmur.ComputeHash(Encoding.UTF8.GetBytes(item.Title)), 0);
            int newDescriptionHash = BitConverter.ToInt32(murmur.ComputeHash(Encoding.UTF8.GetBytes(item.Description)), 0);

            // Check if the hashes are different (if the title or description changed)
            if (existingItem.TitleHash != newTitleHash || existingItem.DescriptionHash != newDescriptionHash)
            {
                // Encrypt new values
                item.EncryptedTitle = _encryptionService.Encrypt(item.Title);
                item.EncryptedDescription = _encryptionService.Encrypt(item.Description);

                // Update the hash values
                item.TitleHash = newTitleHash;
                item.DescriptionHash = newDescriptionHash;

                // Update the entity in the database
                _context.Entry(item).State = EntityState.Modified;
                await _context.SaveChangesAsync();
            }
        }

        // Method to delete a ToDoItem
        public async Task DeleteTodoItemAsync(int id)
        {
            var item = await _context.ToDoItems.FindAsync(id);
            if (item != null)
            {
                // Remove the item from the database context
                _context.ToDoItems.Remove(item);
                await _context.SaveChangesAsync();
            }
        }
    }
}