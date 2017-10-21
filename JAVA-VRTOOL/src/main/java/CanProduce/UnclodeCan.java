package CanProduce;

/**
 * Created by Admin on 2017/10/19.
 */
public class UnclodeCan {
    Message m;
    Sign s;
    int cnt=0, cnt1=0,temp;
    char[] Flag = new char[32];
   /* for(int i=0;i<cnt;i++)
    {*/
        long check=-1;
        long check1=0;
        int pos1,pos2;
        for(int j=0;j<m[i].cnt;j++)
        {
            pos1=m[i].s[j].start/8;
            pos2=m[i].s[j].start%8;
            if(m[i].s[j].dir==0)
            {
               for(int k=0;k<m[i].s[j].len;k++)
                {
                    check^=((long)1<<(pos1*8+pos2));
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
                for(int k=0;k<m[i].s[j].len;k++)
                {
                    check^=((long)1<<(pos1*8+pos2));
                    pos2++;
                    if(pos2==8)
                    {
                        pos2=0;
                        pos1++;
                    }
                }

            }

        }
        for(int j=0;j<64;j++)
        {
            check1|=((((long)check>>(63-j))&1)<<j);
        }

//        cout<<m[i].id<<" "<<check1<<endl;
    }
}
