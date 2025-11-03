// package app;
// import data.FrozenFood;
// import java.util.Scanner;

// //main menu Kasir
// public class kasir {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         FrozenFood produk1 = new FrozenFood("Nugget", 2026, 100, 20000);
//         FrozenFood produk2 = new FrozenFood("Chikuwa", 2026, 100, 10000);
//         FrozenFood produk3 = new FrozenFood("Wings", 2026, 100, 20000);
//         FrozenFood produk4 = new FrozenFood("Marjan", 2026, 100, 15000);
//         FrozenFood produk5 = new FrozenFood("Susu UHT", 2026, 100, 5000);
//         FrozenFood new_produk = new FrozenFood(" ", 0, 0, 0);
  
//         int pilih = 0, pilih2 = 0, pilih3 = 0;
//         do {
//             System.out.println("\nAplikasi Frozen Food");
//             System.out.println("1. Master Barang");
//             System.out.println("2. Transaksi Penjualan");
//             System.out.println("3. Exit");
//             System.out.print("Pilih : ");
//             pilih = sc.nextInt();
//             do {
//                 switch (pilih) {
//                     case 1:
//                         System.out.println("1. Tambah Barang");
//                         System.out.println("2. Ubah Barang");
//                         System.out.println("3. Hapus Barang");
//                         System.out.println("4. Lihat Barang");
//                         System.out.println("5. Kembali ke Menu Utama");
//                         System.out.print("Pilih : ");
//                         pilih2 = sc.nextInt();

//                         switch (pilih2) {
//                             case 1:// tambah barang
//                                 System.out.print("Nama Menu = ");
//                                 String n = sc.next();
//                                 new_produk.setNama(n);
//                                 System.out.print("Harga = ");
//                                 int h = sc.nextInt();
//                                 new_produk.setHarga(h);
//                                 System.out.print("Expired = ");
//                                 int e = sc.nextInt();
//                                 new_produk.setExpired(e);
//                                 System.out.println("Stock = ");
//                                 int s = sc.nextInt();
//                                 new_produk.setStock(s);

//                                 System.out.println("Nama: " + new_produk.getNama());
//                                 System.out.println("Harga: " + new_produk.getHarga());
//                                 System.out.println("Expired: " + new_produk.getExpired());
//                                 System.out.println("Stock: " + new_produk.getStock());
//                                 break;

//                             case 2: // ubah barang
//                                 System.out.println("1. Nugget");
//                                 System.out.println("2. Chikuwa");
//                                 System.out.println("3. Wings");
//                                 System.out.println("4. Marjan");
//                                 System.out.println("5. Susu UHT");
//                                 System.out.println("6. " + new_produk.getNama());
//                                 System.out.println(" Pilih produk yang ingin diubah : ");
//                                 pilih3 = sc.nextInt();
//                                 switch (pilih3) {
//                                     case 1:
//                                         System.out.println("Harga Lama : " + produk1.getHarga());
//                                         System.out.println("Harga Baru : ");
//                                         int hb = sc.nextInt();
//                                         produk1.setHarga(hb);
//                                         System.out.println(" Tahun Lama : " + produk1.getExpired());
//                                         System.out.println(" Tahun Baru : ");
//                                         int eb = sc.nextInt();
//                                         produk1.setExpired(eb);
//                                         System.out.println(" Stock Lama : " + produk1.getStock());
//                                         System.out.println(" Stock Baru : ");
//                                         int sb = sc.nextInt();
//                                         produk1.setStock(sb);
//                                         System.out.println(" Harga Baru : " + produk1.getHarga());
//                                         System.out.println(" Expired Baru : " + produk1.getExpired());
//                                         System.out.println(" Stock Baru : " + produk1.getStock());
//                                         break;
//                                     case 2:
//                                         System.out.println("Harga Lama : " + produk2.getHarga());
//                                         System.out.println("Harga Baru : ");
//                                         int hb2 = sc.nextInt();
//                                         produk2.setHarga(hb2);
//                                         System.out.println(" Tahun Lama : " + produk2.getExpired());
//                                         System.out.println(" Tahun Baru : ");
//                                         int eb2 = sc.nextInt();
//                                         produk1.setExpired(eb2);
//                                         System.out.println(" Stock Lama : " + produk2.getStock());
//                                         System.out.println(" Stock Baru : ");
//                                         int sb2 = sc.nextInt();
//                                         produk2.setStock(sb2);
//                                         System.out.println(" Harga Baru : " + produk2.getHarga());
//                                         System.out.println(" Expired Baru : " + produk2.getExpired());
//                                         System.out.println(" Stock Baru : " + produk2.getStock());

//                                         break;
//                                     case 3:
//                                         System.out.println("Harga Lama : " + produk3.getHarga());
//                                         System.out.println("Harga Baru : ");
//                                         int hb3 = sc.nextInt();
//                                         produk3.setHarga(hb3);
//                                         System.out.println(" Tahun Lama : " + produk3.getExpired());
//                                         System.out.println(" Tahun Baru : ");
//                                         int eb3 = sc.nextInt();
//                                         produk1.setExpired(eb3);
//                                         System.out.println(" Stock Lama : " + produk3.getStock());
//                                         System.out.println(" Stock Baru : ");
//                                         int sb3 = sc.nextInt();
//                                         produk2.setStock(sb3);

//                                         break;
//                                     case 4:
//                                         System.out.println("Harga Lama : " + produk4.getHarga());
//                                         System.out.println("Harga Baru : ");
//                                         int hb4 = sc.nextInt();
//                                         produk2.setHarga(hb4);
//                                         System.out.println(" Tahun Lama : " + produk4.getExpired());
//                                         System.out.println(" Tahun Baru : ");
//                                         int eb4 = sc.nextInt();
//                                         produk1.setExpired(eb4);
//                                         System.out.println(" Stock Lama : " + produk4.getStock());
//                                         System.out.println(" Stock Baru : ");
//                                         int sb4 = sc.nextInt();
//                                         produk2.setStock(sb4);
//                                         System.out.println(" Harga Baru : " + produk4.getHarga());
//                                         System.out.println(" Expired Baru : " + produk4.getExpired());
//                                         System.out.println(" Stock Baru : " + produk4.getStock());

//                                         break;
//                                     case 5:
//                                         System.out.println("Harga Lama : " + produk5.getHarga());
//                                         System.out.println("Harga Baru : ");
//                                         int hb5 = sc.nextInt();
//                                         produk2.setHarga(hb5);
//                                         System.out.println(" Tahun Lama : " + produk5.getExpired());
//                                         System.out.println(" Tahun Baru : ");
//                                         int eb5 = sc.nextInt();
//                                         produk5.setExpired(eb5);
//                                         System.out.println(" Stock Lama : " + produk5.getStock());
//                                         System.out.println(" Stock Baru : ");
//                                         int sb5 = sc.nextInt();
//                                         produk5.setStock(sb5);
//                                         System.out.println(" Harga Baru : " + produk5.getHarga());
//                                         System.out.println(" Expired Baru : " + produk5.getExpired());
//                                         System.out.println(" Stock Baru : " + produk5.getStock());
//                                     case 6:
//                                         System.out.println("Harga Lama : " + new_produk.getHarga());
//                                         System.out.println("Harga Baru : ");
//                                         int hb6 = sc.nextInt();
//                                         new_produk.setHarga(hb6);
//                                         System.out.println(" Tahun Lama : " + new_produk.getExpired());
//                                         System.out.println(" Tahun Baru : ");
//                                         int eb6 = sc.nextInt();
//                                         produk5.setExpired(eb6);
//                                         System.out.println(" Stock Lama : " + new_produk.getStock());
//                                         System.out.println(" Stock Baru : ");
//                                         int sb6 = sc.nextInt();
//                                         produk5.setStock(sb6);
//                                         System.out.println(" Harga Baru : " + new_produk.getHarga());
//                                         System.out.println(" Expired Baru : " + new_produk.getExpired());
//                                         System.out.println(" Stock Baru : " + new_produk.getStock());
//                                         break;
//                                 }

//                                 break;
//                             case 3: // hapus barang
//                                 System.out.println("1. Nugget");
//                                 System.out.println("2. Chikuwa");
//                                 System.out.println("3. Wings");
//                                 System.out.println("4. Marjan");
//                                 System.out.println("5. Susu UHT");
//                                 System.out.println("6. " + new_produk.getNama());
//                                 System.out.println(" Pilih produk yang ingin dihapus : ");
//                                 pilih3 = sc.nextInt();
//                                 switch (pilih3) {
//                                     case 1:
//                                         produk1.hapus();
//                                         break;
//                                     case 2:
//                                         produk2.hapus();
//                                         break;
//                                     case 3:
//                                         produk3.hapus();
//                                         break;
//                                     case 4:
//                                         produk4.hapus();
//                                         break;
//                                     case 5:
//                                         produk5.hapus();
//                                         break;
//                                     case 6:
//                                         new_produk.hapus();
//                                         break;
//                                 }

//                                 break;
//                             case 4:
//                                 System.out.println(produk1.ToString());
//                                 System.out.println(produk2.ToString());
//                                 System.out.println(produk3.ToString());
//                                 System.out.println(produk4.ToString());
//                                 System.out.println(produk5.ToString());
//                                 System.out.println(new_produk.ToString());

//                                 break;
//                             case 5:
//                                 System.out.println("Terimakasih");
//                                 break;

//                         }
//                     case 2:

//                     case 3:

//                 }
//             } while (pilih2 != 5);

//         } while (pilih != 3);

//     }
// }
