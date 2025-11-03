package app;

import data.accountmanager;
import data.cProduk;
import data.cproductmanager;
import java.util.ArrayList;
import java.util.Scanner;

public class menukasir {
    private static cproductmanager manager = new cproductmanager();
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<cProduk> list = new cproductmanager().getproduklist();

    static void app(accountmanager account) {

        int pilih = 0;
        do {
            System.out.println("\nAplikasi Frozen Food");
            System.out.println("1. Lihat Transaksi");
            System.out.println("2. Transaksi Penjualan");

            System.out.println("3. Kembali");

            System.out.print("Pilih : ");
            if (sc.hasNextInt()) {
                pilih = sc.nextInt();

                switch (pilih) {
                    case 1:
                        indeks.antrianTransaksi.viewBelumDiproses();
                        break;
                    case 2:
                        prosestransaksiadmin(account);
                        break;

                    case 3:
                        System.out.println("Terima Kasih");
                        break;
                    default:
                        System.out.println("Invalid Input");
                        break;
                }
            } else {
                System.out.println("Masukkan angka");
                sc.close();
            }
            System.out.println();
        } while (pilih != 3);

    }

    // private static void transaksiPenjualan(accountmanager account) {
    // int pilihTransaksi = 0;
    // System.out.println("\n=== MENU TRANSAKSI ===");

    // System.out.println("Transaksi Penjualan");
    // cNota nota = new cNota(account);
    // nota.setAccountManager(account); // untuk cek member

    // System.out.print("Apakah Anda member? (y/n): ");
    // String statusMember = sc.next();
    // sc.nextLine(); // flush newline

    // if (statusMember.equalsIgnoreCase("y")) {
    // System.out.print("Masukkan ID Member: ");
    // String idMember = sc.nextLine();
    // if (account.isMember(idMember)) {
    // nota.setidpembeli(idMember);
    // System.out.println("Selamat datang, " + idMember + " (Diskon 10%)");
    // } else {
    // System.out.println("Member tidak ditemukan!");
    // return; // keluar dari transaksi jika tidak ditemukan
    // }
    // } else {
    // System.out.print("Masukkan nama pembeli: ");
    // String namaPembeli = sc.nextLine();
    // nota.setidpembeli(namaPembeli); // dianggap non-member
    // }

    // do {
    // System.out.println("\n=== MENU TRANSAKSI ===");
    // System.out.println("1. Tambah Keranjang");
    // System.out.println("2. Hapus Keranjang");
    // System.out.println("3. Bayar");
    // System.out.println("4. Kembali");
    // System.out.print("Pilih: ");
    // pilihTransaksi = sc.nextInt();
    // sc.nextLine(); // flush newline

    // switch (pilihTransaksi) {
    // case 1:
    // int pilihanTambah;
    // do {
    // System.out.println("\n=== TAMBAH BARANG ===");
    // manager.displayproduct();

    // System.out.print("Pilih produk (nomor): ");
    // int idxProduk = sc.nextInt() - 1;

    // if (idxProduk >= 0 && idxProduk < manager.getproduklist().size()) {
    // cProduk produk = manager.getproduklist().get(idxProduk);
    // System.out.print("Jumlah yang dibeli: ");
    // int jumlah = sc.nextInt();
    // if (jumlah > 0 && jumlah <= produk.getStock()) {
    // produk.setStock(produk.getStock() - jumlah);
    // nota.tambahProduk(produk, jumlah);
    // System.out.println("Produk ditambahkan ke keranjang.");
    // } else {
    // System.out.println("Jumlah tidak valid atau stok tidak cukup.");
    // }
    // } else {
    // System.out.println("Produk tidak ditemukan.");
    // }

    // System.out.println("\nDaftar Belanja Sementara:");
    // nota.tampilkanKeranjangSementara(); // tampilkan total sementara

    // System.out.print("Tambah produk lain? (1: Ya, 0: Selesai): ");
    // pilihanTambah = sc.nextInt();
    // } while (pilihanTambah == 1);
    // break;

    // case 2:
    // nota.editProduk(sc);
    // break;

    // case 3:
    // if (nota.prosesPembayaran(sc)) {
    // System.out.println("Terima kasih atas pembelian Anda!");
    // pilihTransaksi = 4; // langsung keluar ke menu utama setelah pembayaran
    // berhasil
    // }
    // break;

    // case 4:
    // System.out.println("Kembali ke menu utama...");
    // break;

    // default:
    // System.out.println("Pilihan tidak valid.");
    // }
    // } while (pilihTransaksi != 4);

    // }

    private static void prosestransaksiadmin(accountmanager account) {
        int pilihan;
        do {
            // Cek apakah antrian kosong di setiap awal loop
            if (indeks.antrianTransaksi.getFront() == null) {
                System.out.println("Semua transaksi sudah diproses. Antrian kosong.");
                break; // Keluar dari loop jika antrian kosong
            }

            System.out.println("\n=== ANTRIAN TRANSAKSI SAAT INI ===");
            // Tampilkan hanya transaksi paling depan yang akan diproses
            strukturdata.csimpul transaksiTerdepan = indeks.antrianTransaksi.getFront();
            System.out.println(
                    "--> Berikutnya: Pembeli/Member: " + transaksiTerdepan.idMember + " | Status: Belum Diproses");
            transaksiTerdepan.nota.tampilkanKeranjangSementara();

            System.out.println("---------------------------------");
            System.out.println("Pilih Aksi:");
            System.out.println("1. Proses Transaksi Terdepan");
            System.out.println("2. Kembali ke Menu Admin");
            System.out.print("Pilihan: ");
            pilihan = sc.nextInt();

            if (pilihan == 1) {
                // Proses transaksi terdepan
                strukturdata.csimpul diproses = indeks.antrianTransaksi.dequeue();
                if (diproses != null) {
                    diproses.status = 1; // Ubah status (meskipun tidak wajib karena sudah di-dequeue)
                    indeks.riwayatTransaksi.add(diproses); // Tambahkan ke riwayat transaksi
                    System.out.println("--> Transaksi untuk " + diproses.idMember + " BERHASIL DIPROSES.");

                    if (account.isMember(diproses.idMember)) { // <-- PERBAIKAN DI SINI
                        String idMember = diproses.idMember;
                        // Hitung total nilai transaksi setelah diskon
                        int nilaiTransaksi = diproses.nota.hitungTotalYangHarusDibayar();

                        // Ambil total belanja lama, tambahkan dengan yg baru, lalu simpan kembali
                        int totalLama = indeks.totalBelanjaMember.getOrDefault(idMember, 0);
                        indeks.totalBelanjaMember.put(idMember, totalLama + nilaiTransaksi);
                        System.out.println("Mencatat total belanja untuk member ID: " + idMember); // Pesan untuk
                                                                                                   // debugging
                    }

                    // if (account.getMemberByUsername(diproses.idMember) != null) {
                    // String idMember = diproses.idMember;
                    // int nilaiTransaksi = diproses.nota.hitungTotalYangHarusDibayar(); // Ambil
                    // total setelah diskon

                    // // Ambil total belanja lama, tambahkan dengan yg baru, lalu simpan kembali
                    // int totalLama = indeks.totalBelanjaMember.getOrDefault(idMember, 0);
                    // indeks.totalBelanjaMember.put(idMember, totalLama + nilaiTransaksi);
                    // }
                }

            }
        } while (pilihan != 2); // Loop akan berhenti jika admin memilih 2
    }

    private static void masterpembeli(accountmanager account) {
        System.out.println("\n=== Master Pembeli ===");
        System.out.println("1. Tambah User");
        System.out.println("2. Hapus User");
        System.out.println("3. Ubah Data User");
        System.out.println("4. Tampilkan Semua User");

        System.out.print("Pilih menu: ");
        int pilih2 = sc.nextInt();
        switch (pilih2) {
            case 1:
                System.out.println("tambah User");
                System.out.print("Masukkan username: ");
                sc = new Scanner(System.in);
                String username = sc.nextLine();
                System.out.print("Masukkan password: ");
                sc = new Scanner(System.in);
                String password = sc.nextLine();
                account.addBasicmember(username, password);

                break;
            case 2:
                System.out.print("Masukkan username Basic User yang ingin dihapus: ");
                sc = new Scanner(System.in);
                String deleteUser = sc.nextLine();
                account.deleteBasicmember(deleteUser);
                break;
            case 3:
                System.out.print("Masukkan username lama: ");
                sc = new Scanner(System.in);
                String oldUser = sc.nextLine();
                System.out.print("Masukkan username baru: ");
                String newUser = sc.nextLine();
                account.updateBasicmember(oldUser, newUser);
                break;
            case 4:
                account.showAllUsers();
                break;
            default:
                break;
        }
    }
}
