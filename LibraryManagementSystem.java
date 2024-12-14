import java.awt.*; import java.awt.event.*;
import java.util.ArrayList; import
java.util.HashMap;
import javax.swing.JOptionPane;
public class LibraryManagementSystem extends Frame {
private CardLayout cardLayout = new CardLayout(); // For switching between screens
private Panel mainPanel = new Panel(); // Central container for all screens private
HashMap<String, String> users = new HashMap<>(); // For User Management private
ArrayList<String> books = new ArrayList<>(); // For Book Management private
HashMap<String, String> borrowedBooks = new HashMap<>(); // For Borrowing System
private ArrayList<String> reservedBooks = new ArrayList<>(); // For Reserved Books
public LibraryManagementSystem() {
setTitle("Library Management System");
setSize(600, 400);
setLayout(new BorderLayout());
mainPanel.setLayout(cardLayout);
add(mainPanel, BorderLayout.CENTER);
createLoginPage();
createLibrarianDashboard(); createUserDashboard();
createUserManagementPage();
createBookManagementPage();
createBorrowingSystemPage();
// Adding dummy data to the system addDummyData();
addWindowListener(new WindowAdapter() {
public void windowClosing(WindowEvent we) {
System.exit(0);
}
});
setVisible(true);
}
private void addDummyData() { // Add
some dummy users users.put("admin",
"admin123"); users.put("john_doe",
"password"); users.put("alice_smith",
"alicepass"); // Add some dummy books
books.add("Java

9
Programming"); books.add("Data Structures and
Algorithms"); books.add("Artificial Intelligence: A
Modern Approach"); books.add("Introduction to
Databases"); books.add("Machine Learning with
Python");
}
private void createLoginPage() { Panel
loginPanel = new Panel();
loginPanel.setLayout(new GridLayout(3, 1, 10, 10));
Label lblWelcome = new Label("Welcome to the Library System", Label.CENTER);
lblWelcome.setFont(new Font("Arial", Font.BOLD, 16));
Button btnLibrarian = new Button("Librarian");
Button btnUser = new Button("User");
btnLibrarian.addActionListener(e -> cardLayout.show(mainPanel, "LibrarianDashboard"));
btnUser.addActionListener(e -> cardLayout.show(mainPanel, "UserDashboard"));
loginPanel.add(lblWelcome);
loginPanel.add(btnLibrarian); loginPanel.add(btnUser);
mainPanel.add(loginPanel, "LoginPage");
}
private void createLibrarianDashboard() { Panel
librarianPanel = new Panel();
librarianPanel.setLayout(new GridLayout(6, 1, 10, 10));
Label lblTitle = new Label("Librarian Dashboard", Label.CENTER);
lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
Button userMgmtBtn = new Button("User Management");
Button bookMgmtBtn = new Button("Book Management");
Button borrowMgmtBtn = new Button("Borrow/Return Books");
Button searchBtn = new Button("Search Books");
Button backBtn = new Button("Back to Login");
userMgmtBtn.addActionListener(e -> cardLayout.show(mainPanel, "UserManagement"));
bookMgmtBtn.addActionListener(e -> cardLayout.show(mainPanel, "BookManagement"));
borrowMgmtBtn.addActionListener(e -> cardLayout.show(mainPanel, "BorrowingSystem"));
searchBtn.addActionListener(e -> searchBookLibrarian());
backBtn.addActionListener(e -> cardLayout.show(mainPanel, "LoginPage"));
librarianPanel.add(lblTitle);
librarianPanel.add(userMgmtBtn);
librarianPanel.add(bookMgmtBtn);
librarianPanel.add(borrowMgmtBtn);

10
librarianPanel.add(searchBtn); librarianPanel.add(backBtn);
mainPanel.add(librarianPanel, "LibrarianDashboard"); }
private void createUserDashboard() {
Panel userPanel = new Panel(); userPanel.setLayout(new
GridLayout(5, 1, 10, 10));
Label lblTitle = new Label("User Dashboard", Label.CENTER); lblTitle.setFont(new
Font("Arial", Font.BOLD, 16));
Button searchBtn = new Button("Search Books");
Button renewBtn = new Button("Renew Book");
Button reserveBtn = new Button("Reserve Book");
Button backBtn = new Button("Back to Login");
searchBtn.addActionListener(e -> searchBookUser());
renewBtn.addActionListener(e -> renewBook()); reserveBtn.addActionListener(e
-> reserveBook()); backBtn.addActionListener(e ->
cardLayout.show(mainPanel, "LoginPage"));
userPanel.add(lblTitle);
userPanel.add(searchBtn);
userPanel.add(renewBtn);
userPanel.add(reserveBtn); userPanel.add(backBtn);
mainPanel.add(userPanel, "UserDashboard");
}
private void createUserManagementPage() { Panel
userMgmtPanel = new Panel();
userMgmtPanel.setLayout(new FlowLayout());
Label lblTitle = new Label("User Management", Label.CENTER); lblTitle.setFont(new
Font("Arial", Font.BOLD, 16));
Label lblName = new Label("Name:");
Label lblPass = new Label("Password:");
Label lblEmail = new Label("Email ID:");
TextField txtName = new TextField(20);
TextField txtPass = new TextField(20); TextField
txtEmail = new TextField(20);
txtPass.setEchoChar('*');
Button addMemberBtn = new Button("Add Member");
Button deleteMemberBtn = new Button("Delete Member");
Button backBtn = new Button("Back to Librarian Dashboard");
addMemberBtn.addActionListener(e -> {
users.put(txtName.getText(), txtPass.getText());
showMessage("Member Added: " + txtName.getText());
});

11
deleteMemberBtn.addActionListener(e -> {
if (users.remove(txtName.getText()) != null) {
showMessage("Member Deleted: " + txtName.getText());
} else {
showMessage("Member Not Found: " + txtName.getText());
} });
backBtn.addActionListener(e -> cardLayout.show(mainPanel, "LibrarianDashboard"));
userMgmtPanel.add(lblTitle);
userMgmtPanel.add(lblName); userMgmtPanel.add(txtName);
userMgmtPanel.add(lblPass); userMgmtPanel.add(txtPass);
userMgmtPanel.add(lblEmail); userMgmtPanel.add(txtEmail);
userMgmtPanel.add(addMemberBtn);
userMgmtPanel.add(deleteMemberBtn);
userMgmtPanel.add(backBtn);
mainPanel.add(userMgmtPanel, "UserManagement");
}
private void createBookManagementPage() { Panel
bookMgmtPanel = new Panel();
bookMgmtPanel.setLayout(new FlowLayout());
Label lblTitle = new Label("Book Management", Label.CENTER);
lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
Label lblBook = new Label("Book Name:");
TextField txtBook = new TextField(20);
Button addBtn = new Button("Add Book");
Button deleteBtn = new Button("Delete Book");
Button backBtn = new Button("Back to Librarian Dashboard");
addBtn.addActionListener(e -> {
books.add(txtBook.getText());
showMessage("Book Added: " + txtBook.getText());
});
deleteBtn.addActionListener(e -> {
if (books.remove(txtBook.getText())) {
showMessage("Book Deleted: " + txtBook.getText());
} else {
showMessage("Book Not Found: " + txtBook.getText());
}
});
backBtn.addActionListener(e -> cardLayout.show(mainPanel, "LibrarianDashboard"));
bookMgmtPanel.add(lblTitle);

12
bookMgmtPanel.add(lblBook); bookMgmtPanel.add(txtBook);
bookMgmtPanel.add(addBtn);
bookMgmtPanel.add(deleteBtn);
bookMgmtPanel.add(backBtn); mainPanel.add(bookMgmtPanel,
"BookManagement"); }
private void createBorrowingSystemPage() { Panel
borrowPanel = new Panel();
borrowPanel.setLayout(new FlowLayout());
Label lblTitle = new Label("Borrow/Return Books", Label.CENTER);
lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
Label lblBook = new Label("Book Name:");
Label lblUser = new Label("Username:");
TextField txtBook = new TextField(20);
TextField txtUser = new TextField(20);
Button borrowBtn = new Button("Borrow");
Button returnBtn = new Button("Return");
Button backBtn = new Button("Back to Librarian Dashboard");
borrowBtn.addActionListener(e -> {
borrowedBooks.put(txtBook.getText(), txtUser.getText()); showMessage("Book
Borrowed: " + txtBook.getText());
});
returnBtn.addActionListener(e -> { if
(borrowedBooks.remove(txtBook.getText()) != null) {
showMessage("Book Returned: " + txtBook.getText());
} else {
showMessage("Book Not Found: " + txtBook.getText());
}
});
backBtn.addActionListener(e -> cardLayout.show(mainPanel, "LibrarianDashboard"));
borrowPanel.add(lblTitle);
borrowPanel.add(lblBook); borrowPanel.add(txtBook);
borrowPanel.add(lblUser); borrowPanel.add(txtUser);
borrowPanel.add(borrowBtn); borrowPanel.add(returnBtn);
borrowPanel.add(backBtn);
mainPanel.add(borrowPanel, "BorrowingSystem");
}
private void searchBookLibrarian() {
String query = JOptionPane.showInputDialog("Enter book name to search:");
if (query != null && !query.isEmpty()) {
boolean found = false;

13
// Convert query to lowercase for case-insensitive comparison query
= query.trim().toLowerCase();
// Loop through the books list to check for a match for
(String book : books) {
if (book.toLowerCase().contains(query)) {
found = true; break; // Exit the loop if a
match is found
}
}
if (found) { showMessage("Book
found: " + query);
} else {
showMessage("Book not found.");
}
}
}
private void searchBookUser() {
String query = JOptionPane.showInputDialog("Enter book name to search:"); if
(query != null && !query.isEmpty()) {
boolean found = false;
// Convert query to lowercase for case-insensitive comparison query
= query.trim().toLowerCase();
// Loop through the books list to check for a match for
(String book : books) {
if (book.toLowerCase().contains(query)) {
found = true;
break; // Exit the loop if a match is found
}
}
if (found) {
showMessage("Book found: " + query);
} else {
showMessage("Book not found.");
}
}
}
private void renewBook() {
showMessage("Book Renewed.");
}
private void reserveBook() {
String bookName = JOptionPane.showInputDialog("Enter book name to reserve:");
if (bookName != null && !bookName.isEmpty()) { reservedBooks.add(bookName);
showMessage("Book Reserved: " + bookName);
}

14
}
private void showMessage(String message) { JOptionPane.showMessageDialog(this,
message); }
public static void main(String[] args) {
new LibraryManagementSystem();
}}