package app;

import data.accountmanager;
import data.accountmanager.Basicmember;
import data.accountmanager.UserAccount;
import data.cNota;
import data.cProduk;
import java.util.Scanner;
import strukturdata.cqueue;
import strukturdata.csimpul;

public class menumember {
    private static Scanner sc = new Scanner(System.in);

    static void app(accountmanager account, String loggedInMemberId) {
        int pilih = 0;
        do {
            System.out.println("\n=== MENU MEMBER ===");
            System.out.println("1. Order");
            System.out.println("2. Lihat Pesanan");

            System.out.println("3. Akun");
            System.out.println("4. Kembali");
            System.out.print("Pilih: ");
            pilih = sc.nextInt();
            sc.nextLine(); // Clear the newline character from the input buffer
            switch (pilih) {
                case 1:
                    buatOrder(true, loggedInMemberId);
                    break;
                case 2:
                    // Sebaiknya buat method khusus untuk melihat pesanan member
                    System.out.println("\n--- Status Pesanan Anda ---");
                    indeks.antrianTransaksi.viewMember(loggedInMemberId);
                    // Kita juga perlu membuat method viewMember di cqueue
                    break;

                case 3:
                    kelolaAkun(account, loggedInMemberId);
                    break;
                case 4:
                    System.out.println("Terimakasih sudah memesan barang. Barang sedang di proses oleh karyawan kami");
                    break;

                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
                    break;
            }
        } while (pilih != 4);

    }

    private static void buatOrder(boolean isMember, String idMember) {

        cNota notaBelanja = new cNota(null);
        String kodeTransaksi = cqueue.generateKodeTransaksi();

        String namaPembeli = "";
        if (!isMember) {
            System.out.print("Masukkan Nama Anda: ");
            namaPembeli = sc.next();
        } else {
            namaPembeli = idMember;
        }

        int pilihan;
        do {
            System.out.println("\n--- Menu Order " + idMember + " (Kode: " + kodeTransaksi + ") ---");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Lihat Keranjang");
            System.out.println("3. Batal Transaksi & Hapus Keranjang");
            System.out.println("4. Selesai & Masuk Antrian");
            System.out.print("Pilih: ");
            pilihan = sc.nextInt();

            switch (pilihan) {
                case 1:
                    indeks.daftarProduk.display();
                    System.out.print("Pilih produk (nomor): ");
                    int idxProduk = sc.nextInt() - 1;
                    cProduk produk = indeks.daftarProduk.getProduk(idxProduk);
                    if (produk != null) {
                        System.out.print("Jumlah: ");
                        int jumlah = sc.nextInt();
                        if (jumlah <= produk.getStock()) {
                            produk.setStock(produk.getStock() - jumlah);

                            // Hitung harga setelah diskon jika member
                            int hargaFinal = produk.getHarga();
                            if (isMember) {
                                hargaFinal = (int) (hargaFinal * 0.95); // Diskon 5%
                                System.out.println("Harga setelah diskon 5%: Rp " + hargaFinal);
                            }
                            cProduk produkDipesan = new cProduk(produk.getNama(), produk.getExpired(), jumlah,
                                    hargaFinal);

                            notaBelanja.tambahProduk(produkDipesan, jumlah);
                            System.out.println(produk.getNama() + " berhasil ditambahkan.");
                        } else {
                            System.out.println("Stok tidak cukup!");
                        }
                    }
                    break;

                case 2:
                    notaBelanja.tampilkanKeranjangSementara();
                    break;
                case 3:
                    // Logika untuk membatalkan transaksi
                    System.out.println("Transaksi dibatalkan.");
                    notaBelanja.kembalikanStokKeDaftarUtama(indeks.daftarProduk);
                    return; // Langsung keluar dari proses order

                case 4:
                    // Masukkan ke antrian utama
                    csimpul simpulBaru = new csimpul(namaPembeli, notaBelanja);
                    indeks.antrianTransaksi.enqueue(simpulBaru);
                    System.out.println("Transaksi selesai dan masuk ke antrian utama.");
                    break;
            }
        } while (pilihan != 4 && pilihan != 3);
    }

    // Tambahkan metode ini di dalam kelas menumember.java

    // Di dalam file app/menumember.java
    private static void kelolaAkun(accountmanager account, String loggedInMemberId) {
        UserAccount user = account.findUserById(loggedInMemberId);

        // Pastikan user ditemukan dan merupakan instance dari Basicmember
        if (user instanceof Basicmember) {
            // Variabel 'member' sekarang dikenal di seluruh blok if ini
            Basicmember member = (Basicmember) user;

            int pilih = 0;
            do {
                System.out.println("\n--- Pengaturan Akun ---");
                System.out.println("1. Lihat Detail Akun");
                System.out.println("2. Ubah Password");
                System.out.println("3. Kembali");
                System.out.print("Pilih: ");
                pilih = sc.nextInt();
                sc.nextLine(); // Membersihkan buffer

                switch (pilih) {
                    case 1:
                        System.out.println("\n-- Detail Akun Anda --");
                        System.out.println("ID Member : " + member.getId());
                        System.out.println("Username  : " + member.getUsername());
                        // Menggunakan getPassword() yang benar
                        System.out.println("Password  : " + member.getpassword());
                        System.out.println("----------------------");
                        break;
                    case 2:
                        System.out.println("\n-- Ubah Password --");
                        System.out.print("Masukkan password lama Anda: ");
                        String passLama = sc.nextLine();

                        // Verifikasi password lama
                        if (member.getpassword().equals(passLama)) {
                            System.out.print("Masukkan password baru: ");
                            String passBaru = sc.nextLine();
                            System.out.print("Konfirmasi password baru: ");
                            String konfirmasiPass = sc.nextLine();

                            if (passBaru.equals(konfirmasiPass)) {
                                if (!passBaru.isEmpty()) {
                                    member.setpassword(passBaru);
                                    System.out.println("--> Password berhasil diubah!");
                                } else {
                                    System.out.println("x: Password baru tidak boleh kosong.");
                                }
                            } else {
                                System.out.println("x: Konfirmasi password baru tidak cocok.");
                            }
                        } else {
                            System.out.println("x: Password lama yang Anda masukkan salah.");
                        }
                        break;
                    case 3:
                        System.out.println("Kembali ke menu member...");
                        break;
                    default:
                        System.out.println("x: Pilihan tidak valid.");
                }
            } while (pilih != 3);

        } else {
            // Jika user yang login bukan member atau tidak ditemukan
            System.out.println("x: Terjadi kesalahan. Akun member tidak valid atau tidak ditemukan.");
        }
    }
}
