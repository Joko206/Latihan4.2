import java.util.Scanner;
import java.util.Stack;

// Kelas TextEditor untuk mensimulasikan text editor
class TextEditor {
    private StringBuilder text; // Menyimpan isi teks
    private Stack<String> undoStack; // Stack untuk undo
    private Stack<String> redoStack; // Stack untuk redo

    // Konstruktor
    public TextEditor() {
        text = new StringBuilder();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    // Fungsi untuk menampilkan semua teks
    public void show() {
        if (text.length() == 0) {
            System.out.println("Teks kosong.");
        } else {
            System.out.println("Isi Teks: " + text.toString());
        }
    }

    // Fungsi untuk menambahkan teks
    public void write(String newText) {
        undoStack.push(text.toString()); // Simpan keadaan sebelumnya
        text.append(newText); // Tambahkan teks baru
        redoStack.clear(); // Kosongkan redo stack
        System.out.println("Teks ditambahkan: " + newText);
    }

    // Fungsi untuk mengembalikan isi teks ke isi sebelumnya
    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(text.toString()); // Simpan keadaan saat ini untuk redo
            text = new StringBuilder(undoStack.pop()); // Kembalikan keadaan sebelumnya
            System.out.println("Undo berhasil.");
        } else {
            System.out.println("Tidak ada tindakan yang dapat di-undo.");
        }
    }

    // Fungsi untuk memulihkan pengembalian isi teks ke isi yang lebih baru
    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(text.toString()); // Simpan keadaan saat ini untuk undo
            text = new StringBuilder(redoStack.pop()); // Kembalikan keadaan yang lebih baru
            System.out.println("Redo berhasil.");
        } else {
            System.out.println("Tidak ada tindakan yang dapat di-redo.");
        }
    }
}

// Kelas Main untuk menjalankan program
public class Main {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Show Text");
            System.out.println("2. Write Text");
            System.out.println("3. Undo");
            System.out.println("4. Redo");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");

            pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline yang tersisa

            switch (pilihan) {
                case 1:
                    textEditor.show();
                    break;
                case 2:
                    System.out.print("Masukkan teks: ");
                    String newText = scanner.nextLine();
                    textEditor.write(newText);
                    break;
                case 3:
                    textEditor.undo();
                    break;
                case 4:
                    textEditor.redo();
                    break;
                case 5:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 5);

        scanner.close(); // Menutup scanner
    }
}