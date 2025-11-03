package app;

import data.accountmanager;
import data.cProduk;
import data.cproductmanager;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import strukturdata.csimpul;

public class menuowner {
    private static Scanner sc = new Scanner(System.in);
    private static cproductmanager manager = new cproductmanager();

    public static void app(accountmanager account) {

        int pilih = 0;
        do {
            System.out.println("\n=== MENU OWNER ===");
            System.out.println("1. Nilai Penjulan sudah dibayar");
            System.out.println("2. Nilai Penjualan belum dibayar");
            System.out.println("3. Laporan Penjualan");
            System.out.println("4. Menu Barang");

            System.out.println("5. Lihat Daftar Akun");
            System.out.println("6. kembali");
            System.out.print("Pilih: ");
            pilih = sc.nextInt();
            sc.nextLine(); // Membersihkan buffer input
            switch (pilih) {
                case 1:
                    lihatLaporanPenjualan(1);
                    break;
                case 2:
                    lihatLaporanPenjualan(0);
                    break;
                case 3:
                    laporan();
                    break;
                case 4:

                    menubarang();
                    break;

                case 5:
                    lihatakun(account);
                    break;
                case 6:
                    System.out.println("Kembali");
                    break;

                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
                    break;
            }
        } while (pilih != 6);

    }

    private static void menubarang() {
        System.out.println("\n=== MENU BARANG ===");
        int pilih = 0;
        do {
            System.out.println("1. Daftar Barang");
            System.out.println("2. Edit Barang");
            System.out.println("3. Kembali");
            System.out.print("Pilih: ");
            pilih = sc.nextInt();
            sc.nextLine(); // Membersihkan buffer input
            switch (pilih) {
                case 1:
                    indeks.daftarProduk.display();
                    break;
                case 2:
                    ubahDataBarang();
                    break;
                case 3:
                    System.out.println("Kembali ke menu utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
                    break;
            }
        } while (pilih != 3);
    }

    private static void lihatakun(accountmanager account) {
        int pilihan = 0;
        do {
            System.out.println("\n=== DAFTAR AKUN ===");
            System.out.println("1. Tampilkan Akun Member");
            System.out.println("2. Tampilkan Akun Karyawan");
            System.out.println("3. Tampilkan Akun Owner");
            System.out.println("4. Kembali");
            System.out.print("Pilih: ");
            pilihan = sc.nextInt();
            sc.nextLine(); // Membersihkan buffer input
            switch (pilihan) {
                case 1:
                    showamember(account);
                    break;
                case 2:
                    showakaryawan(account);
                    break;

                case 3:
                    showaowner(account);
                    break;
                case 4:
                    System.out.println("Kembali ke menu utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
                    break;
            }
        } while (pilihan != 4);
    }

    private static void lihatLaporanPenjualan(int status) {

        int totalNilai = 0;
        String judul = (status == 1) ? "SUDAH DIPROSES" : "BELUM DIPROSES";
        System.out.println("\n=== LAPORAN PENJUALAN " + judul + " ===");

        if (status == 1) {
            // JIKA MELIHAT YANG SUDAH DIPROSES, ITERASI DARI RIWAYAT
            if (indeks.riwayatTransaksi.isEmpty()) {
                System.out.println("x: Belum ada penjualan yang diproses.");
                return;
            }

            for (strukturdata.csimpul trxSelesai : indeks.riwayatTransaksi) {
                // Kita tidak perlu cek status lagi, karena semua di sini pasti sudah diproses
                int nilaiTransaksi = trxSelesai.nota.hitungTotalSebelumDiskon();
                System.out.println("- Pembeli/Member: " + trxSelesai.idMember + " | Total: Rp " + nilaiTransaksi);
                totalNilai += nilaiTransaksi;
            }

        } else {
            // JIKA MELIHAT YANG BELUM DIPROSES, ITERASI DARI ANTRIAN (logika lama Anda)
            strukturdata.csimpul current = indeks.antrianTransaksi.getFront();
            if (current == null) {
                System.out.println("x: Tidak ada transaksi yang sedang mengantri.");
                return;
            }

            while (current != null) {
                if (current.status == status) {
                    int nilaiTransaksi = current.nota.hitungTotalSebelumDiskon();
                    System.out.println("- Pembeli/Member: " + current.idMember + " | Total: Rp" + nilaiTransaksi);
                    totalNilai += nilaiTransaksi;
                }
                current = current.next;
            }
            System.out.println("---------------------------------------");
            System.out.println("TOTAL NILAI PENJUALAN: Rp" + totalNilai);
            System.out.println("=======================================");
        }
    }

    // Tambahkan metode ini di dalam file app/menuowner.java

    private static void ubahDataBarang() {
        System.out.println("\n=== UBAH DATA BARANG ===");
        // Menampilkan semua produk dari linked list di kelas indeks
        indeks.daftarProduk.display();

        System.out.print("Pilih nomor barang yang akan diubah: ");
        int pilihan = sc.nextInt();
        sc.nextLine(); // Membersihkan buffer

        // Mengambil objek produk dari linked list berdasarkan nomor pilihan
        // Nomor pilihan dikurangi 1 karena indeks list dimulai dari 0
        data.cProduk produkDipilih = indeks.daftarProduk.getProduk(pilihan - 1);

        if (produkDipilih != null) {
            System.out.println("Anda memilih: " + produkDipilih.getNama());

            // Menampilkan data lama dan meminta data baru untuk harga
            System.out.println("Harga saat ini: " + produkDipilih.getHarga());
            System.out.print("Masukkan harga baru: ");
            int hargaBaru = sc.nextInt();
            produkDipilih.setHarga(hargaBaru);

            // Menampilkan data lama dan meminta data baru untuk stok
            System.out.println("Stok saat ini: " + produkDipilih.getStock());
            System.out.print("Masukkan stok baru: ");
            int stokBaru = sc.nextInt();
            sc.nextLine(); // Membersihkan buffer
            produkDipilih.setStock(stokBaru);

            System.out.println("--> Data barang " + produkDipilih.getNama() + " berhasil diperbarui.");

        } else {
            System.out.println("x: Pilihan tidak valid. Barang tidak ditemukan.");
        }
    }

    private static void showamember(accountmanager account) {

        account.showAllUsers();
    }

    private static void showakaryawan(accountmanager account) {

        account.showadmin();
    }

    private static void showaowner(accountmanager account) {

        account.showowner();
    }

    private static void laporan() {
        int pilih = 0;
        do {
            System.out.println("\n=== LAPORAN PENJUALAN ===");
            System.out.println("1. Laporan Penjualan Harian (Per Barang)");
            System.out.println("2. Laporan Total Belanja Member");
            System.out.println("3. Grafik Penjualan Bulanan");
            System.out.println("4. Kembali");
            System.out.print("Pilih: ");

            pilih = sc.nextInt();
            sc.nextLine();

            switch (pilih) {
                case 1:
                    laporanPenjualanHarian();
                    break;
                case 2:
                    laporanBelanjaMember();
                    break;
                case 3:
                    tampilkanGrafikPenjualan();
                    break;
                case 4:
                    System.out.println("Kembali ke menu utama...");
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        } while (pilih != 4);
    }

    private static void laporanPenjualanHarian() {
        System.out.println("\n--- LAPORAN PENJUALAN HARIAN (PER BARANG) ---");
        if (indeks.riwayatTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi yang diproses hari ini.");
            return;
        }

        HashMap<String, Integer> penjualanPerBarang = new HashMap<>();
        for (strukturdata.csimpulproduk p = indeks.daftarProduk.getHead(); p != null; p = p.next) {
            penjualanPerBarang.put(p.produk.getNama(), 0);
        }
        int totalPendapatan = 0;

        for (csimpul trx : indeks.riwayatTransaksi) {
            // Filter hanya untuk transaksi hari ini
            if (trx.tanggalTransaksi.equals(LocalDate.now())) {
                for (int i = 0; i < trx.nota.getProdukDibeli().size(); i++) {
                    cProduk produk = trx.nota.getProdukDibeli().get(i);
                    int jumlah = trx.nota.getJumlahBeli().get(i);
                    int nilai = produk.getHarga() * jumlah;

                    penjualanPerBarang.put(produk.getNama(),
                            penjualanPerBarang.get(produk.getNama()) + nilai);
                    totalPendapatan += nilai;
                }
            }
        }

        System.out.println("Total Pendapatan Hari Ini: Rp " + totalPendapatan);
        System.out.println("------------------------------------------");
        int nomor = 1;
        for (Map.Entry<String, Integer> entry : penjualanPerBarang.entrySet()) {
            System.out.println(nomor + ". " + entry.getKey() + " : Rp " + entry.getValue());
            nomor++;
        }
        System.out.println("------------------------------------------");
    }

    // FITUR 2: Laporan Total Belanja per Member
    private static void laporanBelanjaMember() {
        System.out.println("\n--- LAPORAN TOTAL BELANJA MEMBER ---");
        if (indeks.totalBelanjaMember.isEmpty()) {
            System.out.println("Belum ada data belanja dari member.");
            return;
        }

        int nomor = 1;
        for (Map.Entry<String, Integer> entry : indeks.totalBelanjaMember.entrySet()) {
            System.out.println(nomor + ". " + entry.getKey() + " : Rp " + entry.getValue());
            nomor++;
        }
        System.out.println("------------------------------------");
    }

    private static void tampilkanGrafikPenjualan() {
        System.out.println("\n--- GRAFIK PENJUALAN BULAN INI ---");
        if (indeks.riwayatTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi bulan ini.");
            return;
        }

        HashMap<String, Integer> kuantitas = new HashMap<>();
        HashMap<String, Integer> nilaiPenjualan = new HashMap<>();
        LocalDate hariIni = LocalDate.now();

        for (strukturdata.csimpulproduk p = indeks.daftarProduk.getHead(); p != null; p = p.next) {
            kuantitas.put(p.produk.getNama(), 0);
            nilaiPenjualan.put(p.produk.getNama(), 0);
        }

        for (csimpul trx : indeks.riwayatTransaksi) {
            if (trx.tanggalTransaksi.getMonth() == hariIni.getMonth()
                    && trx.tanggalTransaksi.getYear() == hariIni.getYear()) {
                for (int i = 0; i < trx.nota.getProdukDibeli().size(); i++) {
                    cProduk produk = trx.nota.getProdukDibeli().get(i);
                    int jumlah = trx.nota.getJumlahBeli().get(i);
                    int nilai = produk.getHarga() * jumlah;
                    // Akumulasi Kuantitas
                    kuantitas.put(produk.getNama(), kuantitas.get(produk.getNama()) + jumlah);
                    // Akumulasi Nilai (Rupiah)
                    nilaiPenjualan.put(produk.getNama(), nilaiPenjualan.get(produk.getNama()) + nilai);
                }
            }
        }

        System.out.println("(Skala: 1 'X' = 1 item terjual)");
        for (Map.Entry<String, Integer> entry : kuantitas.entrySet()) {
            String namaBarang = entry.getKey();
            int totalNilai = nilaiPenjualan.get(namaBarang);
            // int jumlahX = totalNilai / 10000; // Pembulatan ke bawah
            int totalKuantitas = entry.getValue();

            System.out.print(String.format("%-15s: ", namaBarang)); // Format agar rapi
            for (int i = 0; i < totalKuantitas; i++) {
                System.out.print("X");
            }
            System.out.println(" Rp " + totalNilai);

        }
        System.out.println("------------------------------------");
    }

}
