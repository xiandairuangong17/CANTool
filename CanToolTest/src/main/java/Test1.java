//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.PrintStream;
//import java.util.Scanner;
//
//import static sun.nio.ch.IOStatus.EOF;
//
//public class Test1 {
//	//随机生成CAN信息
//	int cnt=0,cnt1=0,temp;
//	char flag[32];
//	//定义CAN信号的结构体
//	class sign
//	{
//		int start,len,dir;
//		double a,b,c,d;
//		char signal_name[32],unit[32],node_name[32],temp;
//	}
//	//定义CAN信息的结构体
//	class message
//	{
//		int id,len,cnt;
//		char message_name[32],node_name[32];
//		sign s[30];
//	}m[1010];
//	public void print(int x)
//	{
//		if(x<10)
//			System.out.printf("%c",'0'+x);
//		else
//			System.out.printf("%c",'A'+x-10);
//	}
//	public void Transform(unsigned long long x,int k)
//	{
//		for(int i=k-1;i>=0;i--)
//		{
//			print((x>>(i*4))&15);
//		}
//	}
//
//	public void Transform1(unsigned long long x,int k)
//	{
//		for(int i=0;i<k;i+=2)
//		{
//			print((x>>((i+1)*4))&15);
//			print((x>>(i*4))&15);
//		}
//	}
//
//	class main(){
////		freopen("data.in","r",stdin);
////		freopen("data1.txt","w",stdout);
//		 final String INPUT = "data.in";
//		 final String OUTPUT = "G:\\课设\\CANTool\\CanToolTest\\data1.txt";
//		// open I/O files
//		FileInputStream instream = null;
//		PrintStream outstream = null;
//
//		try {
//			instream = new FileInputStream(INPUT);
//			outstream = new PrintStream(new FileOutputStream(OUTPUT));
//			System.setIn(instream);
//			System.setOut(outstream);
//		} catch (Exception e) {
//			System.err.println("Error Occurred.");
//		}
//
//		Scanner in = new Scanner(System.in);
//		for (;in.hasNext();) {
//			int x = in.nextInt();
//			System.out.println(x);
//		}
//
//		System.err.println("done.");
//		while(scanf("%s",flag)!=EOF)
//		{
//			if(flag[0]=='B')
//			{
//				cnt++;
//				temp=cnt-1;
//
//			}
//			else
//			{
//				scanf("%s : %d|%d@%d%c (%lf,%lf) [%lf|%lf] %s %s",
//						m[temp].s[cnt1].signal_name,&m[temp].s[cnt1].start,
//                  &m[temp].s[cnt1].len, &m[temp].s[cnt1].dir,&m[temp].s[cnt1].temp,
//                   &m[temp].s[cnt1].a,&m[temp].s[cnt1].b, &m[temp].s[cnt1].c,
//                   &m[temp].s[cnt1].d,m[temp].s[cnt1].unit,
//					m[temp].s[cnt1].node_name);
//				if(m[temp].s[cnt1].temp=='+')
//				{
//					cnt1++;
//					m[temp].cnt++;
//				}
//
//			}
//		}
//		srand((unsigned)time(NULL));
//		for(int i=0;i<1000;i++)
//		{
//			printf("t");
//			int temp1=(rand()%(cnt));
//			//cout<<endl<<cnt<<" "<<temp1<<endl;
//			Transform(m[temp1].id,3);
//			Transform(m[temp1].len,1);
//			unsigned long long data=0;
//			int x,y,pos1,pos2;
//			for(int j=0;j<m[temp1].cnt;j++)
//			{
//				int flag1=(rand()%(2));;
//				if(flag1)
//				{
//					x=(m[temp1].s[j].c-m[temp1].s[j].b)/m[temp1].s[j].a;
//					y=(m[temp1].s[j].d-m[temp1].s[j].b)/m[temp1].s[j].a;
//                long long temp2 = (rand()%(y-x+1))+x;
//					pos1=m[temp1].s[j].start/8;
//					pos2=m[temp1].s[j].start%8;
//					if(m[temp1].s[j].dir==0)
//					{
//						for(int k=0;k<m[temp1].s[j].len;k++)
//						{
//							data|=((long long)((temp2>>(m[temp1].s[j].len-1-k))&1)<<(pos1*8+pos2));
//							pos2--;
//							if(pos2==-1)
//							{
//								pos2=7;
//								pos1++;
//							}
//						}
//					}
//					else
//					{
//						data|=(temp2<<m[temp1].s[j].start);
//					}
//				}
//			}
//			Transform1(data,m[temp1].len*2);
//			puts("\r");
//		}
//
//
//	}
//
//
//
//}
//
//
