package CanProduce;

import static sun.nio.ch.IOStatus.EOF;

/**
 * Created by Admin on 2017/10/19.
 */
public class CanProduce {
	//定义CAN信号的结构体
	class sign
	{
		int start,len,dir;
		double a,b,c,d;
//		String signal_name;
		char singnal_name[] = new char[32];
//		String unit;
		char unit [] = new char[32];
//		String node_name;
		char node_name [] = new char[32];
//		String temp;
		char temp;
	}
	//定义CAN信息的结构体
	class message
	{
		int id,len,cnt;
		String message_name;
		String node_name;
		sign s;
	}
	message m;

	int cnt=0,cnt1=0,temp;
	String flag;
	//随机生成CAN信息
	void print(int x)
	{
		if(x<10)
//			printf("%c",'0'+x);
			System.out.printf("%c",0+x);
		else
//			printf("%c",'A'+x-10);
		System.out.printf("%c",'A'+x-10);
	}
	void Transform(long x,int k)
	{
		for(int i=k-1;i>=0;i--)
		{
//			print((x>>(i*4))&15);
			System.out.print((x>>(i*4))&15);
		}
	}

	void Transform1(long x,int k)
	{
		for(int i=0;i<k;i+=2)
		{
//			print((x>>((i+1)*4))&15);
//			print((x>>(i*4))&15);
			System.out.print((x>>((i+1)*4))&15);
			System.out.print((x>>(i*4))&15);
		}
	}

	int main()
	{
		freopen("data.in","r",stdin);
		freopen("data1.txt","w",stdout);
		while(scanf("%s",flag)!=EOF)
		{
			if(flag[0]=='B')
			{
				cnt++;
				temp=cnt-1;
				scanf("%d %s %d %s",&m[temp].id,m[temp].message_name,
                  &m[temp].len,m[temp].node_name);
				cnt1=m[temp].cnt=0;
			}
			else
			{
				scanf("%s : %d|%d@%d%c (%lf,%lf) [%lf|%lf] %s %s",
						m[temp].s[cnt1].signal_name,&m[temp].s[cnt1].start,
                  &m[temp].s[cnt1].len, &m[temp].s[cnt1].dir,&m[temp].s[cnt1].temp,
                   &m[temp].s[cnt1].a,&m[temp].s[cnt1].b, &m[temp].s[cnt1].c,
                   &m[temp].s[cnt1].d,m[temp].s[cnt1].unit,
					m[temp].s[cnt1].node_name);
				if(m[temp].s[cnt1].temp=='+')
				{
					cnt1++;
					m[temp].cnt++;
				}

			}
		}
		srand((unsigned)time(NULL));
		for(int i=0;i<1000;i++)
		{
			printf("t");
			int temp1=(rand()%(cnt));
			//cout<<endl<<cnt<<" "<<temp1<<endl;
			Transform(m[temp1].id,3);
			Transform(m[temp1].len,1);
			unsigned long long data=0;
			int x,y,pos1,pos2;
			for(int j=0;j<m[temp1].cnt;j++)
			{
				int flag1=(rand()%(2));
				if(flag1)
				{
					x=(m[temp1].s[j].c-m[temp1].s[j].b)/m[temp1].s[j].a;
					y=(m[temp1].s[j].d-m[temp1].s[j].b)/m[temp1].s[j].a;
                long long temp2 = (rand()%(y-x+1))+x;
					pos1=m[temp1].s[j].start/8;
					pos2=m[temp1].s[j].start%8;
					if(m[temp1].s[j].dir==0)
					{
						for(int k=0;k<m[temp1].s[j].len;k++)
						{
							data|=((long long)((temp2>>(m[temp1].s[j].len-1-k))&1)<<(pos1*8+pos2));
							pos2--;
							if(pos2==-1)
							{
								pos2=7;
								pos1++;
							}
						}
					}
					else
					{
						data|=(temp2<<m[temp1].s[j].start);
					}
				}
			}
			Transform1(data,m[temp1].len*2);
			puts("\r");
		}


	}
}
