package spaceInvaders;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class UpgradeItem {
	private int x;
	private int y;
	private int barWidth=150;
	private String text;
	private Game game;
	private int cost;
	private int numUpgradeLevels;
	private int upgradeLevel=0;
	private boolean mouseOver = false;
	private String tag;

	public UpgradeItem(Game g, int x, int y, String text,int cost,int numUpgLev,String tag) {
		game=g;
		this.x=x;
		this.y=y;
		this.text=text;
		this.cost=cost;
		numUpgradeLevels=numUpgLev;
		this.tag=tag;
	}
	
	public int buy(int currPoints){
		if(currPoints>=cost&&upgradeLevel<numUpgradeLevels){
			upgradeLevel++;
			currPoints-=cost;
		}
		return currPoints;
	}
	
	public boolean isInside(int mouseX,int mouseY){
		if(mouseX>=x+130&&mouseX<=x+230&&mouseY>=y-10&&mouseY<=y+10){
			return true;
		}
		return false;
	}
	
	public void mouseOver(boolean isOver) {
		mouseOver=isOver;
	}
	
	public int getLevel(){
		return upgradeLevel;
	}
	
	public void setNumUpgradeLevels(int newNum){
		numUpgradeLevels = newNum;
	}
	public int getNumUpgradeLevels(){
		return numUpgradeLevels;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.ORANGE);
		g.drawString(text+":", x, y+g.getFontMetrics().getHeight());
		g.setColor(Color.DARK_GRAY);
		for(int i=0;i<numUpgradeLevels;i++){
			int xCoord =(int)(x+(i*((((double)150)/numUpgradeLevels)+1)));
			int yCoord =y+g.getFontMetrics().getHeight()+10;
			g.drawRect(xCoord, yCoord,(int)(((double)150)/numUpgradeLevels)+1, 10);
			if(i<upgradeLevel){
				g.setColor(Color.BLUE);
				g.fillRect(xCoord, yCoord, (int)(((double)150)/numUpgradeLevels)+2,10);
				g.setColor(Color.DARK_GRAY);
			}
		}
		if(upgradeLevel<numUpgradeLevels){
			if(mouseOver){
				g.setColor(Color.LIGHT_GRAY);
			} else {
				g.setColor(Color.DARK_GRAY);
				
			}
			g.fillRect(x+130, y, 100, 20);
			g.setColor(Color.RED);
			g.drawString("Upgrade " + cost + " pts", x+140, y+15);
		}
	}
}