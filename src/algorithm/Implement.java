package algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Implement {
    private List<Data> data = new ArrayList<>();
    private List<List<Data>> cluster = new ArrayList<>();
    private Map<Integer, Data> center = new HashMap<>();
    private int cluster_count;
    private boolean needClassify;


    Implement() throws Exception {
        importData();
        for (int i = 0; i < cluster_count; i++) {
            cluster.add(new ArrayList<>());
            center.put(i, data.get(i));
        }

    }

    private void importData() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("data"));
        String dat = reader.readLine();
        cluster_count = Integer.valueOf(dat);
        while((dat = reader.readLine()) != null){
            data.add(new Data(dat.split(",")));
        }
        reader.close();
    }

    public void classify(){
        needClassify = false;
        for (Data sample : data) {
            int minCluster = getMinCluster(sample);
            if (!cluster.get(minCluster).contains(sample)) {
                needClassify = true;
                cluster.forEach((list) -> list.remove(sample));
                cluster.get(minCluster).add(sample);
            }
        }
        updateCenter();
        if (needClassify)
            classify();
    }

    private int getMinCluster(Data sample){
        int index = 0;
        double min_distance = sample.getDistance(center.get(0).getData());
        double distance;
        for (int i = 0; i < cluster_count; i++) {
            distance = sample.getDistance(center.get(i).getData());
            if (distance < min_distance){
                min_distance = distance;
                index = i;
            }
        }
        return index;
    }

    private void updateCenter(){
        for (int i = 0; i < cluster_count; i++) {
            Data newCenter = new Data(center.get(0).getData().length);
            List<Data> list = cluster.get(i);
            if (list.size() == 0)
                continue;
            list.forEach(newCenter::addData);
            newCenter.toAvg(list.size());
            center.put(i, newCenter);
        }
    }

    public void print(){
        for (int i = 0; i < cluster_count; i++) {
            System.out.println("----第" + (i + 1) + "个聚类----");
            System.out.println("聚类中心：" + center.get(i).toString());
            cluster.get(i).forEach((d)-> System.out.println(d.toString()));
        }
    }

}
