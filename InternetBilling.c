#include<stdio.h>
#include<conio.h>

typedef struct date
{
   int day;
   int month;
   int year;
}date;

typedef struct customer
{
    int id;
    char name[20];
    char phon_num[20];
    char address[20];
    char branch[20];
    unsigned balance;  
    date lastpayment;  
}customer;

void printChar(char x,int n)
{
    while(n>0)
    {
        printf("%c",x);
        n--;
    }
    printf("\n");
}
void addCustomer()
{
    printChar('=',128);
    printf("\t\t\t\t\t\tCUSTOMER ADDITION PORTAL\n");
    printChar('=',128);
    FILE *fp;
    fp=fopen("Customers.txt","a");
    if(fp==NULL)
    {
        printf("\nERROR IN OPENING FILE!");
        return;
    }
        
    customer c;

    printf("Enter customer id: ");
    scanf("%d",&c.id);

    printf("Enter customer name: ");
    fflush(stdin);
    gets(c.name);

    printf("Enter phone number: ");
    fflush(stdin);
    gets(c.phon_num);

    printf("Enter address: ");
    fflush(stdin);
    gets(c.address);

    printf("Enter the nearest branch: ");
    fflush(stdin);
    gets(c.branch);

    printf("Enter due balance in rupees: ");
    scanf("%u",&c.balance);

    printf("Enter date of last payment (dd mm yyyy): ");
    scanf("%d %d %d",&c.lastpayment.day,&c.lastpayment.month,&c.lastpayment.year);

    int flag=fwrite(&c,sizeof(customer),1,fp);

    if(flag)
        printf("\nCustomer Details Added Successfully...");
    else
        printf("\nError in writing into the file...");

    fclose(fp);
}

void searchCustomer()
{
    printChar('=',128);
    printf("\t\t\t\t\t\tCUSTOMER SEARCH PORTAL\n");
    printChar('=',128);
    FILE *fp;
    fp=fopen("Customers.txt","r");

    if(fp==NULL)
    {
        printf("\nERROR IN OPENING FILE!");
        return;
    }

    customer c;
    int id,flag=0;

    printf("Enter customer id: ");
    scanf("%d",&id);
    
    while(fread(&c,sizeof(customer),1,fp))
    {
        if(c.id==id)
        {
            flag=1;
            printf("\n");
            printf("Customer id  : %d\n",c.id);
            printf("Customer name: %s\n",c.name);
            printf("Phone number : %s\n",c.phon_num);
            printf("Address      : %s\n",c.address);
            printf("Branch       : %s\n",c.branch);
            printf("Due Balance  : Rs. %u\n",c.balance);
        }
    }

    if(flag==0)
        printf("\nCustomer not found...");  

    fclose(fp);
}

void makePayment()
{
    printChar('=',128);
    printf("\t\t\t\t\t\tPAYMENT PORTAL\n");
    printChar('=',128);
    FILE *fp;
    fp=fopen("Customers.txt","r+");

    if(fp==NULL)
    {
        printf("\nERROR IN OPENING FILE!");
        return;
    }

    customer c;

    int id ,amount,flag=0;

    printf("Enter customer id: ");
    scanf("%d",&id);

    while(fread(&c,sizeof(customer),1,fp))
    {
        if(c.id==id)
        {
            
            flag=1;
            printf("Customer name       : %s\n",c.name);
            printf("Due Balance         : Rs. %u\n",c.balance);
            printf("Last date of payment: %d/%d/%d",c.lastpayment.day,c.lastpayment.month,c.lastpayment.year);
            
            if(c.balance==0)
            {
                printf("\nNo dues left...");
                return;
            }

            printf("\n\nEnter amount to be paid: ");
            scanf("%d",&amount);

            if(amount<=c.balance)
            {
                c.balance-=amount;
                printf("\nPayment Successful!!");
            }
            else
                printf("\nAmount greater than the due balance...");
            break;
        }
    }

    if(flag==1)
    {
        fseek(fp,-sizeof(customer),SEEK_CUR);
        fwrite(&c,sizeof(customer),1,fp);
    }
    else
        printf("\n\nCustomer not found...");

    fclose(fp);
}

void removeCustomer()
{
    printChar('=',128);
    printf("\t\t\t\t\t\tCUSTOMER DELETION PORTAL\n");
    printChar('=',128);
    int id,flag=0;

    printf("Enter the id of the Customer to be deleted: ");
    scanf("%d",&id);

    FILE *fp1,*fp2;
    fp1=fopen("Customers.txt","r");
    fp2=fopen("temp.txt","w");

    customer c;

    while(fread(&c,sizeof(customer),1,fp1))
    {
        if(c.id==id)
        {
            flag=1;
            continue;
        }
        fwrite(&c,sizeof(customer),1,fp2);
    }

    fclose(fp1);
    fclose(fp2);

    if(flag==0)
    {
        printf("\nCustomer not found...");
    }
    else
    {
        printf("\nCustomer removed successfully...");
    }

    remove("Customers.txt");
    rename("temp.txt","Customers.txt");
}

int main()
{
    printChar('*',128);
    printf("\t\t\t\t\t\tWORLDLINK COMMUNICATIONS LIMITED\n");
    printf("\t\t\t\t\t\t\tKathmandu, Nepal\n");
    printChar('*',128);
    
    printf("\nWelcome to Intertet Billing Portal of Worldlink.");
    printf("\nPress any key to continue...");
    getch();
    
    while(1)
    {
        printf("\n");
        printChar('=',128);
        printf("1. Add Customer\n");
        printf("2. Search Customer\n");
        printf("3. Make Payment\n");
        printf("4. Remove Customer\n");
        printf("5. Exit\n");
        printChar('=',128);
        
        printf("Enter your choice : ");
        int choice;
        scanf("%d",&choice);
        
        switch(choice)
        {
            case 1:
                addCustomer();
                break;
                
            case 2:
                searchCustomer();
                break;
            
            case 3:
                makePayment();
                break;
                
            case 4:
                removeCustomer();
                break;
                
            case 5:
                printf("\n");
                printChar('*',128);
                printf("\t\t\t\t\t\tTHANK YOU\n");
                printf("\t\t\t\t\tDesigned by Pratika Dhungana...\n");
                printChar('*',128);
                getch();
                return 0;
                
            default:
                printf("\nInvalid choice...\n");
        }
    }
}