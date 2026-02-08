# 🎮 Adam Asmaca (Hangman Game)

Java ile geliştirilmiş, konsol tabanlı klasik kelime tahmin oyunu.

## 🎯 Nasıl Oynanır?
1. Oyun rastgele bir kelime seçer (Örn: JAVASCRIPT).
2. Her turda bir harf tahmin edersiniz.
3. Yanlış tahminlerde darağacı adım adım çizilir.
4. **5 Canınız** vardır.
   - Harf hatası: **-1 Can**
   - Yanlış kelime tahmini (Risk): **-2 Can**

## 🛠️ Teknik Özellikler
- **Dinamik ASCII Çizimi:** Kalan cana göre değişen grafikler.
- **String İşlemleri:** `toUpperCase`, `substring`, `charAt` metodları aktif kullanıldı.
- **Algoritma:** Kelimeyi maskeleme (`_ _ _ _`) ve döngüsel kontrol mantığı.

## ▶️ Çalıştırma
Bu klasörde terminali açıp şu komutları yazın:
```bash
javac AdamAsmaca.java
java AdamAsmaca