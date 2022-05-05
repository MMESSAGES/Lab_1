package P1;
import java.io.*;

public class MagicSquare {

    public static boolean isLegalMagicSquare(String fileName){
        File f = new File(fileName);
        String str_read = null;
        BufferedReader in = null;
        try{
            in=new BufferedReader(new FileReader(f));
        }catch(FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        }//文件打开失败
        try {
            str_read = in.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        int i=0,j=0;
        String[] strings_mod = str_read.split("\t");
        int length = strings_mod.length;
        int[][] num = new int[length][length];
        for(;j < length; j++){
            try{
                num[i][j] = Integer.parseInt(strings_mod[j]);
            }catch(Exception e){
                System.out.println("The wrong format!");
                return false;
            }
            if(num[i][j] < 0){
                System.out.println("It's a negative number.");
            }
        }
        for(i = 1;i < length; i++){
            try {
                str_read = in.readLine();
            } catch (IOException ioException) {
                break;
            }
            if(str_read ==null){
                break;
            }
            //读取结束标志
            String[] strings = str_read.split("\t");
            if(strings.length != length){
                System.out.println("The length of the different row is different.");
                return false;
            }//更新strings并检验长度
            for(j=0;j < length; j++){
                try{
                    num[i][j] = Integer.parseInt(strings[j]);
                }catch(Exception e){
                    System.out.println("The wrong format!");
                    return false;
                }
                if(num[i][j] < 0){
                    System.out.println("It's a negative number.");
                    return false;
                }//检验合法性
            }//处理每行
        }

        try{
            if((str_read = in.readLine()) != null){
                System.out.println("The row is bigger than the column.");
                return false;
            }
        }catch (IOException ioException) {
            ioException.printStackTrace();
        }//行大于列
        if (i < j){
            System.out.println("The column is bigger than the row.");
            return false;
        }//列大于行

        //判断是否为合格幻方
        int row = 0;
        for(j = 0; j < length;j++){
            row += num[0][j];
        }
        int sum = row;//初始化sum值
        for(i = 1;i < length; i++){
            row = 0;
            for(j = 0; j < length;j++){
                row += num[i][j];
            }
            if(row != sum){
                System.out.println("The row's sum is wrong.");
                return false;
            }
        }//检验行
        int column;
        for(j = 0;j < length; j++){
            column = 0;
            for(i = 0; i < length;i++){
                column += num[i][j];
//                System.out.println(column);
            }
            if(column != sum){
                System.out.println("The "+j+" column's sum is wrong.");
                return false;
            }
        }//检验列
        int l_diagonal = 0;
        for(i = 0;i < length; i++){
            l_diagonal += num[i][i];
        }if(l_diagonal != sum){
            System.out.println("The left diagonal's sum is wrong.");
            return false;
        }//左对角线
        int r_diagonal = 0;
        for(i = 0;i < length; i++){
            r_diagonal += num[length-1-i][i];
        }if(r_diagonal != sum){
            System.out.println("The right diagonal's sum is wrong.");
            return false;
        }//右对角线

        return true;
    }

    public static boolean generateMagicSquare(int n) {
        if((n % 2) == 0 || n < 0 ){
            System.out.println("The n is in wrong format.");
            return false;
        }
        PrintWriter pw = null;
        try{
            pw = new PrintWriter("src\\txt\\6.txt");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        int[][] magic = new int[n][n];
        int row = 0, col = n / 2, i, j, square = n * n;
        for (i = 1; i <= square; i++) {
            magic[row][col] = i;
            if (i % n == 0)
                row++;
            else {
                if (row == 0)
                    row = n - 1;
                else
                    row--;
                if (col == (n - 1))
                    col = 0;
                else
                    col++;
            }
        }
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++)
                pw.print(magic[i][j] + "\t");
            pw.println();
        }

        pw.close();
        return true;
    }

    public static void main(String[] args){
        boolean sign;

        sign = MagicSquare.isLegalMagicSquare("src\\txt\\1.txt");
        if (!sign){
            System.out.println("1.txt is not a magic squares.");
        }else{
            System.out.println("1.txt is a magic squares.");
        }

        sign = MagicSquare.isLegalMagicSquare("src\\txt\\2.txt");
        if (!sign){
            System.out.println("2.txt is not a magic squares.");
        }else{
            System.out.println("2.txt is a magic squares.");
        }

        sign = MagicSquare.isLegalMagicSquare("src\\txt\\3.txt");
        if (!sign){
            System.out.println("3.txt is not a magic squares.");
        }else{
            System.out.println("3.txt is a magic squares.");
        }

        sign = MagicSquare.isLegalMagicSquare("src\\txt\\4.txt");
        if (!sign){
            System.out.println("4.txt is not a magic squares.");
        }else{
            System.out.println("4.txt is a magic squares.");
        }

        sign = MagicSquare.isLegalMagicSquare("src\\txt\\5.txt");
        if (!sign){
            System.out.println("5.txt is not a magic squares.");
        }else{
            System.out.println("5.txt is a magic squares.");
        }

        sign = MagicSquare.generateMagicSquare(5);
        if( !sign ){
            System.out.println("The 6.txt is not generated.");
        }else{
            sign = MagicSquare.isLegalMagicSquare("src\\txt\\6.txt");
            if (!sign){
                System.out.println("6.txt is not a magic squares.");
            }else{
                System.out.println("6.txt is a magic squares.");
            }
        }
    }

}
