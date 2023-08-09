using CertificateEncryptionProj.Data;
using CertificateEncryptionProj.Models;
using CertificateEncryptionProj.Security;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CertificateEncryptionProj.Repo
{
    public class TodoRepository
    {
        private readonly ApplicationDbContext _context;
        private readonly EncryptionService _encryptionService;

        public TodoRepository(ApplicationDbContext context, EncryptionService encryptionService)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));
            _encryptionService = encryptionService ?? throw new ArgumentNullException(nameof(encryptionService));
        }

        public async Task<ToDoItems> CreateTodoItemAsync(ToDoItems item)
        {
            item.EncryptedTitle = _encryptionService.Encrypt(item.Title);
            item.EncryptedDescription = _encryptionService.Encrypt(item.Description);
            _context.ToDoItems.Add(item);
            await _context.SaveChangesAsync();
            return item;
        }

        public async Task<IEnumerable<ToDoItems>> GetTodoItemsAsync(string userId)
        {
            var items = await _context.ToDoItems.Where(x => x.UserId == userId).ToListAsync();
            foreach (var item in items)
            {
                item.Title = _encryptionService.Decrypt(item.EncryptedTitle);
                item.Description = _encryptionService.Decrypt(item.EncryptedDescription);
            }
            return items;
        }

        public async Task<ToDoItems> GetTodoItemAsync(int id)
        {
            var item = await _context.ToDoItems.FindAsync(id);
            if (item != null)
            {
                item.Title = _encryptionService.Decrypt(item.EncryptedTitle);
                item.Description = _encryptionService.Decrypt(item.EncryptedDescription);
            }
            return item;
        }

        public async Task UpdateTodoItemAsync(ToDoItems item)
        {
            item.EncryptedTitle = _encryptionService.Encrypt(item.Title);
            item.EncryptedDescription = _encryptionService.Encrypt(item.Description);
            _context.Entry(item).State = EntityState.Modified;
            await _context.SaveChangesAsync();
        }

        public async Task DeleteTodoItemAsync(int id)
        {
            var item = await _context.ToDoItems.FindAsync(id);
            if (item != null)
            {
                _context.ToDoItems.Remove(item);
                await _context.SaveChangesAsync();
            }
        }
    }
}