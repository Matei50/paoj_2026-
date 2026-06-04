# Food Delivery Platform

Acesta este proiectul individual pentru cursul PAO - Etapa 1 și Etapa 2.

## 1.1 Acțiuni în sistem
Sistemul suportă următoarele acțiuni:
1. Creare cont client
2. Autentificare client
3. Afișare restaurante (sortate alfabetic)
4. Căutare restaurant după nume
5. Afișare meniu restaurant
6. Adăugare produs în coșul curent
7. Afișare coș de cumpărături
8. Plasare comandă
9. Verificare status comandă
10. Raportare detalii comenzi, clienți și produse
11. Anulare comandă
12. Adăugare restaurant la favorite
13. Afișare restaurante favorite

## 1.2 Tipuri de obiecte din domeniu
Proiectul folosește următoarele obiecte de domeniu (pachetul `model`):
- `Adresa`
- `Client` (moștenește `Utilizator`)
- `Curier` (moștenește `Utilizator`)
- `Utilizator` (clasă abstractă)
- `CodComanda` (clasă imutabilă)
- `Comanda`
- `Cos`
- `Produs`
- `Restaurant`
- `Review`
- `CategorieRestaurant` (Enum)
- `Plata` (Enum)
