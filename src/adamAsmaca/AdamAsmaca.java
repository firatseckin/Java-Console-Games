package adamAsmaca;

import java.util.Scanner;
import java.util.Random;

/**
 * Adam Asmaca Oyunu
 * Konsol tabanlı, ASCII grafikli ve kelime tahmin özellikli oyun.
 */
public class AdamAsmaca {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Kelime havuzu
        String[] kelimeler = {"JAVASCRIPT", "PROGRAMLAMA", "BILGISAYAR", "YAZILIM", "MUHENDIS", "ALGORITMA"};

        // Rastgele kelime seçimi
        String secilenKelime = kelimeler[random.nextInt(kelimeler.length)];

        // Gizli kelimenin oluşturulması (_ _ _ _ _)
        char[] gizliKelime = new char[secilenKelime.length()];
        for (int i = 0; i < gizliKelime.length; i++) {
            gizliKelime[i] = '_';
        }

        int can = 5;
        boolean oyunBitti = false;

        System.out.println(">>> ADAM ASMACA OYUNUNA HOŞGELDİN <<<");
        System.out.println("Kelimeyi bulmak için 5 hakkın var! İster harf gir, ister risk alıp kelimeyi yaz.");

        // --- OYUN DÖNGÜSÜ ---
        while (!oyunBitti) {
            // 1. Durumu Çiz
            adamCiz(can);

            // 2. Gizli Kelimeyi Yazdır
            System.out.print("Kelime: ");
            for (char c : gizliKelime) {
                System.out.print(c + " ");
            }
            System.out.println("\nKalan Can: " + can);

            // 3. Kullanıcıdan Tahmin Al
            System.out.print("Tahmininiz (Harf veya Kelime): ");
            String girdi = scanner.next().toUpperCase();

            // --- TAHMİN KONTROLÜ ---
            if (girdi.length() == 1) {
                // DURUM A: Harf Tahmini
                char harf = girdi.charAt(0);
                boolean harfBulundu = false;

                for (int i = 0; i < secilenKelime.length(); i++) {
                    if (secilenKelime.charAt(i) == harf) {
                        gizliKelime[i] = harf;
                        harfBulundu = true;
                    }
                }

                if (harfBulundu) {
                    System.out.println("--> Süper! Harf doğru.");
                } else {
                    can--;
                    System.out.println("--> Yanlış harf! Bir canın gitti.");
                }

            } else {
                // DURUM B: Kelime Tahmini (Risk)
                if (girdi.equals(secilenKelime)) {
                    System.out.println("--> HELAL OLSUN! Riski aldın ve kazandın!");
                    secilenKelimeyiAc(gizliKelime, secilenKelime); // Görsel olarak tamamla
                    oyunBitti = true;
                    // Döngüyü kırmadan önce son durumu göstermek için continue diyebiliriz
                    // veya direkt aşağıda kazanma kontrolüne bırakabiliriz.
                } else {
                    can -= 2;
                    System.out.println("--> YANLIŞ KELİME! Büyük risk, 2 canın gitti!");
                }
            }

            // --- OYUN BİTİŞ KONTROLLERİ ---

            // 1. Kazanma Kontrolü (Dizide hiç '_' kalmadıysa)
            boolean kazandiMi = true;
            for (char c : gizliKelime) {
                if (c == '_') {
                    kazandiMi = false;
                    break;
                }
            }

            if (kazandiMi) {
                System.out.println("\n*****************************************");
                System.out.println("🎉 TEBRİKLER! KELİMEYİ BULDUNUZ: " + secilenKelime);
                System.out.println("*****************************************");
                oyunBitti = true;
            }

            // 2. Kaybetme Kontrolü
            if (can <= 0 && !kazandiMi) {
                adamCiz(can); // Son halini çiz
                System.out.println("\n💀 KAYBETTİNİZ! Doğru Kelime: " + secilenKelime);
                oyunBitti = true;
            }
        }
        scanner.close();
    }

    // Kelime doğru tahmin edilirse diziyi tamamen dolduran yardımcı metot
    private static void secilenKelimeyiAc(char[] gizli, String kelime) {
        for(int i=0; i<kelime.length(); i++) {
            gizli[i] = kelime.charAt(i);
        }
    }

    /**
     * Kalan can sayısına göre ASCII sanatını ekrana çizer.
     * @param kalanCan Oyuncunun kalan hakkı
     */
    public static void adamCiz(int kalanCan) {
        System.out.println("\n------- DURUM -------");

        // Hata önleyici: Can eksiye düşerse en son resmi (5. indeks) göster
        int resimSirasi = 5 - kalanCan;
        if (resimSirasi > 5) resimSirasi = 5;

        String[] resimler = {
                "  +---+\n  |   |\n      |\n      |\n      |\n=========", // 5 Can
                "  +---+\n  |   |\n  O   |\n      |\n      |\n=========", // 4 Can
                "  +---+\n  |   |\n  O   |\n  |   |\n      |\n=========", // 3 Can
                "  +---+\n  |   |\n  O   |\n /|   |\n      |\n=========", // 2 Can
                "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n=========", // 1 Can
                "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n========="  // 0 Can (Ölüm)
        };

        System.out.println(resimler[resimSirasi]);
    }
}
