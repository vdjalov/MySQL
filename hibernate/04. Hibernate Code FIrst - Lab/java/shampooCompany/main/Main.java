package shampooCompany.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import shampooCompany.ingredients.AmoniumChloride;
import shampooCompany.ingredients.BasicChemicalIngredient;
import shampooCompany.ingredients.BasicIngredient;
import shampooCompany.ingredients.Mint;
import shampooCompany.ingredients.Nettle;
import shampooCompany.ingredients.Strawberry;
import shampooCompany.labels.BasicLabel;
import shampooCompany.shampoos.BasicShampoo;
import shampooCompany.shampoos.FiftyShades;
import shampooCompany.shampoos.FreshNuke;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) {
       
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("shampoo-company");
    	EntityManager em = emf.createEntityManager();
    	
    	
    	em.getTransaction().begin();
    	
    	BasicChemicalIngredient am = new AmoniumChloride();
    	BasicIngredient mint = new Mint();
    	BasicIngredient nettle = new Nettle();
    	BasicIngredient straw = new Strawberry();
    	
    	BasicLabel labelOne = new BasicLabel("Fresh Nuke", "Contains Mint and Nettle");
     	BasicLabel labelTwo = new BasicLabel("Fifty Shades", "Contains Mint, Nettle and Strawberry");
    	BasicShampoo shampooONE = new FreshNuke(labelOne);
    	BasicShampoo shampooTWO = new FiftyShades(labelTwo);
    	
    	shampooONE.getIngredients().add(mint);
    	shampooONE.getIngredients().add(nettle);
    	
    	shampooTWO.getIngredients().add(mint);
    	shampooTWO.getIngredients().add(nettle);
    	shampooTWO.getIngredients().add(straw);
    	
    	em.persist(shampooONE);
    	em.persist(shampooTWO);
    	
    	em.getTransaction().commit();
    	em.close();
    }
}












