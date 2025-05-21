# TugbaLang Syntax Highlighter Projesi

##  Proje Hakkında

Bu proje, "Programlama Dilleri" dersi kapsamında geliştirilmiştir. Amaç, kendi tanımladığımız basit bir programlama dili olan **TugbaLang** için gerçek zamanlı (real-time) bir sözdizimi vurgulama (syntax highlighting) sistemi oluşturmaktır.

## TugbaLang Nedir?

TugbaLang, tamamen özgün olarak tasarladığım basit bir programlama dilidir. Yapısı, Türkçeye benzer olacak şekilde geliştirilmiştir. Dil, temel olarak aşağıdaki yapıları destekler:

* Değişken tanımlama: `belirle`
* Yazdırma komutu: `yaz`
* Koşulu ifadeler: `eger ... ise { ... }`
* Döngüler: `dongu ... iken { ... }`
* Yorum satırı: `#` ile başlayan satırlar

##  Projede Gerçekleştirilenler

* **Lexer (sözcük çözümleyici)**: Kodun içindeki kelimeleri (token) tanır.
* **Parser (sözdizim kontrolü)**: Yazılan kodun dil kurallarına uyup uymadığını kontrol eder.
* **GUI (grafiksel arayüz)**: Java Swing ile kod yazma ekranı sunar ve yazdıkça token'ları renklendirir.

## Renklendirme Özellikleri

* Anahtar kelimeler (belirle, yaz, eger, dongu, vs.): **Mavi**
* Değişken adları: **Siyah**
* Sayılar: **Mor**
* Operatörler (+, =, >, < vs.): **Kırmızı**
* Noktalama ve blok işaretleri (; { }): **Gri**
* Yorumlar: **Yeşil**

##  Kullanım

* Eclipse ya da herhangi bir Java IDE ile projeyi çalıştırabilirsiniz.
* ".jar" dosyası oluşturarak masaüstü uygulaması gibi kullanmak da mümkün.
* Yazdığınız kod anında analiz edilir ve renklendirilir.

##  Notlar

Bu projede hiçbir hazır syntax highlighting kütüphanesi kullanılmamıştır. Tüm analiz ve renklendirme işlemleri elle, Java koduyla yazılmıştır. Bu sayede projenin mantığını daha iyi öğrendim.

##  Demo

Demo videosu ayrıca sunulacaktır.

##  BNF (Backus-Naur Formu)

Projenin gramer tanımı "TugbaLang\_BNF.txt" dosyasında bulunmaktadır.

> Hazırlayan: Tugba Çevik
> Ders: Programlama Dilleri
> Teslim: Haziran 2025
