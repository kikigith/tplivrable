package com.bootcamp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.bootcamp.tp.dao.LivrableRepository;
import com.bootcamp.tp.entities.Etat;
import com.bootcamp.tp.entities.Livrable;

public class RemplirLivrableTest {
	
	private LivrableRepository lr=new LivrableRepository("tpservice");
	
	@Test
	public void ajoutLivrable(){
		List<Object> lieux=new ArrayList<Object>(2);
		lieux.add("cotonou");
		lieux.add("parakou");
		List<Object> objectifs=new ArrayList<Object>(2);
		objectifs.add("obj1");
		objectifs.add("obj2");
		for(int i=0;i<20; i++){
			Etat et=(i%2==0) ? Etat.realisation:Etat.livre;
			int j=i+1;
			Livrable li=new Livrable(j, "ref"+ j, "description"+ j, objectifs, lieux, et);
			try {
				lr.create(li);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
