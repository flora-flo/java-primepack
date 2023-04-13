/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

public class Produit {

        private int id;

    private float prix;

    private int stock;

    private boolean isavailable;

    private String nom;

    private String image;

    private Categorie category;

    public Produit() {
    }

    public Produit(float prix, int stock, boolean isavailable, String nom, String image, Categorie category) {
        this.prix = prix;
        this.stock = stock;
        this.isavailable = isavailable;
        this.nom = nom;
        this.image = image;
        this.category = category;
    }

    public Produit(int id, float prix, int stock, boolean isavailable, String nom, String image, Categorie category) {
        this.id = id;
        this.prix = prix;
        this.stock = stock;
        this.isavailable = isavailable;
        this.nom = nom;
        this.image = image;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isIsavailable() {
        return isavailable;
    }

    public void setIsavailable(boolean isavailable) {
        this.isavailable = isavailable;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Categorie getCategory() {
        return category;
    }

    public void setCategory(Categorie category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", prix=" + prix + ", stock=" + stock + ", isavailable=" + isavailable + ", nom=" + nom + ", image=" + image + ", category=" + category + '}';
    }


}
