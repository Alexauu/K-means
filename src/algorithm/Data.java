package algorithm;


public class Data {
    private double[] data;

    Data(String[] strings){
        int length = strings.length;
        data = new double[length];
        for (int i = 0; i < length; i++) {
            data[i] = Double.valueOf(strings[i]);
        }
    }

    Data(int size){
        this.data = new double[size];
    }

    public double getDistance(double[] point){
        int length = data.length;
        double dis = 0;
        for (int i = 0; i < length; i++) {
            dis += Math.pow(data[i] - point[i], 2);
        }
        return dis;
    }

    public double[] getData() {
        return data;
    }

    public void toAvg(int count) {
        for (int i = 0; i < data.length; i++) {
            data[i] /= count;
        }
    }

    public void addData(Data food){
        double[] foodData = food.getData();
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] += foodData[i];
        }
    }

    @Override
    public String toString() {
        if (data.length == 1)
        return data[0] + " ";
        else{
            StringBuilder string = new StringBuilder();
            string.append("(");
            for (double dat : data) {
                string.append(dat).append(",");
            }
            string.deleteCharAt(string.lastIndexOf(","));
            string.append(")");
            return string.toString();
        }
    }
}
