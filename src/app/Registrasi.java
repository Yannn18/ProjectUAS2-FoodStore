package app;

import data.accountmanager;
import java.util.Scanner;

public class Registrasi {

  private static Scanner input = new Scanner(System.in);

  static void app(accountmanager account) {

    int pilihan = 0;

    do {
      System.out.println("\n=== MENU ===");
      System.out.println("1. Registrasi Akun");
      System.out.println("2. Login Guest");
      System.out.println("3. Login Member");
      System.out.println("4. Login Admin");
      System.out.println("5. Login Owner");
      System.out.println("6. Kembali");
      System.out.print("Pilih menu: ");
      if (input.hasNextInt()) {
        pilihan = input.nextInt();
        input.nextLine();

        switch (pilihan) {
          case 1:

            createAccount(account);
            System.out.println("Akun berhasil dibuat!");
            break;

          case 2:

            menuguest.app();
            break;
          case 3:

            member(account);
            break;

          case 4:

            admin(account);
            break;
          case 5:

            owner(account);
            break;

          case 6:
            System.out.println("");
            break;

          default:
            System.out.println("Pilihan tidak valid!");
            break;
        }
      } else {
        System.out.println("Masukkan Angka");
        input.next();
      }
      System.out.println();
    } while (pilihan != 6);

  }

  // buat akun member
  private static void createAccount(accountmanager account) {
    System.out.println("=== REGISTRASI AKUN ===");
    System.out.print("Masukkan username: ");
    input = new Scanner(System.in);
    String username = input.nextLine();
    System.out.print("Masukkan password: ");
    String password = input.nextLine();
    account.addBasicmember(username, password);
  }

  private static void member(accountmanager account) {
    System.out.println("=== LOGIN MEMBER ===");
    input = new Scanner(System.in);
    System.out.print("Masukkan username: ");
    String loginUsername = input.nextLine();
    System.out.print("Masukkan password: ");
    String loginPassword = input.nextLine();

    boolean sukses = account.login(loginUsername, loginPassword);

    if (sukses) {

      // 1. Dapatkan objek member berdasarkan username
      accountmanager.Basicmember memberData = account.getMemberByUsername(loginUsername);

      // 2. Cek jika data member ditemukan dan ambil ID-nya
      if (memberData != null) {
        String memberID = memberData.getId();
        System.out.println("Login berhasil! Selamat datang, " + loginUsername + " (ID: " + memberID + ")!");
        // 3. Teruskan ID Member, bukan username
        menumember.app(account, memberID);
      } else {
        System.out.println("Error: Tidak dapat menemukan data member setelah login.");
      }
    } else {
      System.out.println("Login gagal. Username atau password salah.");
    }
  }

  // Login admin
  private static void admin(accountmanager account) {
    System.out.println("=== LOGIN ADMIN ===");
    System.out.print("Masukkan username: ");
    String loginUsername = input.nextLine();

    System.out.print("Masukkan password: ");
    String loginPassword = input.nextLine();

    // Verifikasi menggunakan method loginAdmin
    accountmanager.UserWithPassword adminAkun = account.loginAdmin(loginUsername, loginPassword);

    if (adminAkun != null) {
      System.out.println("Login berhasil! Selamat datang, " + loginUsername + "!");
      menukasir.app(account);
    } else {
      System.out.println("Login gagal. Username atau password salah.");
    }
  }

  // Login owner
  private static void owner(accountmanager account) {
    System.out.println("=== LOGIN OWNER ===");
    System.out.print("Masukkan username: ");
    String loginUsername = input.nextLine();

    System.out.print("Masukkan password: ");
    String loginPassword = input.nextLine();

    // Verifikasi menggunakan method loginOwner
    accountmanager.owneraccount ownerAkun = account.loginOwner(loginUsername, loginPassword);

    if (ownerAkun != null) {
      System.out.println("Login berhasil! Selamat datang, " + loginUsername + "!");
      menuowner.app(account);
    } else {
      System.out.println("Login gagal. Username atau password salah.");
    }
  }

  // lihat semua akun
  private static void showaccount(accountmanager account) {
    System.out.println("=== TAMPILKAN SEMUA USER ===");
    input = new Scanner(System.in);
    account.showAllUsers();
  }
}