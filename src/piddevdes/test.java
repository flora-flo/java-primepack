/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevdes;

import entites.Categorie;
import entites.Produit;
import services.CategorieService;
import services.ProduitService;

public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Categorie c = new Categorie();
       /* c.setDescription("jhj");
        c.setIcone("sdsdsd");
        c.setNom("12");
        c.setNote(25);
//
//        CategorieService cs = new CategorieService();
//
//        cs.insert(c);
        c.setId(2);
//        c.setNom("xxxxxxxxxxxx");
//        cs.update(c);
//        cs.delete(c);
//
//        System.out.println(cs.getAll());
//        System.out.println(cs.getOne(1));

        Produit p = new Produit(12, "tssqs", 145, true, "dsds", "sdd", c);

        ProduitService ps = new ProduitService();
        ps.insert(p);

        p.setId(1);
        p.setNom("p1");
        ps.update(p);
        System.out.println(ps.getAll());
        System.out.println(ps.getOne(1));
        ps.delete(p);*/
    }

}
