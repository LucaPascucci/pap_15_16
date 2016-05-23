package test.gof2;

public class Model {

	private boolean [][] table;
	private boolean [][] tmpTable;
	private int row,column;
	
	public Model(int row,int column){
		this.row=row;
		this.column=column;
		table = new boolean [row][column];
		tmpTable = new boolean [row][column];
	}
	
	public void setCells(boolean [][] table){
		this.table=table;
	}
	
	public boolean[][] getTable(){
		return table;
	}
	
	public void calculate(int start,int end){
		for(int i=start;i<end;i++){
			for(int j=0;j<column;j++){
				int nLive=0;
				
				if(table[(row+i-1)%row][(column+j-1)%column])
					nLive++;
				
				if(table[(row+i-1)%row][j])
					nLive++;
				
				if(table[(row+i-1)%row][(column+j+1)%column])
					nLive++;
				
				if(table[i][(column+j-1)%column])
					nLive++;
				
				if(table[i][(column+j+1)%column])
					nLive++;
				
				if(table[(row+i+1)%row][(column+j-1)%column])
					nLive++;
				
				if(table[(row+i+1)%row][j])
					nLive++;
				
				if(table[(row+i+1)%row][(column+j+1)%column])
					nLive++;
				
				
				if(nLive<2 && table[i][j]){
					tmpTable[i][j]=false;
				}else if((nLive==2 || nLive==3) && table[i][j]){
					tmpTable[i][j]=true;
				}else if(nLive>3 && table[i][j]){
					tmpTable[i][j]=false;
				}else if(nLive==3 && !table[i][j]){
					tmpTable[i][j]=true;
				}else{
					tmpTable[i][j]=false;
				}
				
			}
		}
	}
	
	public void refresh(int start,int end){
		for(int i=start;i<end;i++){
			for(int j=0;j<column;j++){
				if(tmpTable[i][j]){
					table[i][j]=true;
				}else{
					table[i][j]=false;
				}
			}
		}
		
	}
	
	public int getRow(){
		return row;
	}
	
	public int getColumn(){
		return column;
	}
}
