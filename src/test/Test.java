package test;

import java.io.IOException;
import java.util.ArrayList;

public class Test {
	public  static void main(String[] args) throws IOException
	{
		ArrayList<double[]> dataSet1=new ArrayList<double[]>();
		ArrayList<double[]> dataSet=new ArrayList<double[]>();
		ReadFile rf = new ReadFile();
		dataSet = rf.inport("D:/13.xlsx");
		System.out.print(dataSet.size());
		KMeans k=new KMeans(0);
		
		//初始化一个Kmean对象，将k置为10
		for(int j= 100;;j=j+1){
			k.setDataSet(dataSet);
			k.setK(j);
			//设置原始数据集
			
			//执行算法
			k.execute();
			//得到聚类结果
			ArrayList<ArrayList<double[]>> cluster=k.getCluster();
			//查看结果
			if( cluster.size()>200)
				continue;
			ArrayList<double[]> center = k.getCenter();
			int f=0;
			for(int i =0;i<cluster.size();i++){
				for(int kk=0;kk<cluster.get(i).size();kk++){
					if(k.GetDistance(cluster.get(i).get(kk)[0], cluster.get(i).get(kk)[1], center.get(i)[0], center.get(i)[1])>2)
						f=1;
				}
			}
			if(f==1 )
				continue;
			for(int i=0;i<cluster.size();i++)
			{
				k.printDataArray(cluster.get(i), "cluster["+i+"]");
			}
			System.out.print(cluster.size());
			break;
		}
		
		
	}
}
