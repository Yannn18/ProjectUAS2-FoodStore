package app;

import data.cNota;
import data.cProduk;
import java.util.Scanner;
import strukturdata.cqueue;
import strukturdata.csimpul;

public class menuguest {
    private static Scanner sc = new Scanner(System.in);

    static void app() {
        int pilih = 0;
        buatOrder(false, null);
        // do {
        // System.out.println("\n=== MENU GUEST ===");
        // System.out.println("1. Order");
        // System.out.println("2. Lihat Pesanan");
        // System.out.println("3. Hapus Pesanan");
        // System.out.println("4. Kembali");
        // pilih = sc.nextInt();
        // switch (pilih) {
        // case 1:
        // buatOrder(false, null);
        // break;
        // case 2:
        // lihatpesanan();

        // break;
        // case 3:
        // hapuspesanan();
        // break;
        // case 4:
        // System.out.println("Terimakasih sudah memesan barang. Barang sedang di proses
        // oleh karyawan kami");
        // break;

        // default:
        // System.out.println("Pilihan tidak valid, silakan coba lagi.");
        // break;
        // }
        // } while (pilih != 4);

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
            System.out.println("\n--- Menu Order " + namaPembeli + " (Kode: " + kodeTransaksi + ") ---");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Hapus pesanan & Keluar");
            System.out.println("3. Lihat Keranjang");
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
                    System.out.println("Transaksi dibatalkan.");
                    // PANGGIL METODE PENGEMBALIAN STOK DI SINI
                    notaBelanja.kembalikanStokKeDaftarUtama(indeks.daftarProduk);
                    return; // Keluar dari method order

                case 3:
                    notaBelanja.tampilkanKeranjangSementara();
                    break;
                case 4:
                    // Masukkan ke antrian utama
                    csimpul simpulBaru = new csimpul(namaPembeli, notaBelanja);
                    indeks.antrianTransaksi.enqueue(simpulBaru);
                    System.out.println("Transaksi selesai dan masuk ke antrian utama.");
                    break;
            }
        } while (pilihan != 4);
    }

}
