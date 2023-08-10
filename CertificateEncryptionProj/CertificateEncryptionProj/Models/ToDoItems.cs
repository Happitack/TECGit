namespace CertificateEncryptionProj.Models
{
    public class ToDoItems
    {
        public int Id { get; set; }
        public string UserId { get; set; }
        public string Title { get; set; }
        public string Description { get; set; }
        public byte[] EncryptedTitle { get; set; }
        public byte[] EncryptedDescription { get; set; } 
        public int TitleHash { get; set; }
        public int DescriptionHash { get; set; }
        public bool IsEditing { get; set; }

    }
}