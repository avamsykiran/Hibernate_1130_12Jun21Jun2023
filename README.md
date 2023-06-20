JPA & Hibernate 5
------------------------------------------------------------

    JPA is the ORM specification from JavaEE. Hibernate is the most popular implementation of JPA.

    Object Relation Mapping - ORM


    Data                        OOP                                                RDBMS                        JPA Annotaions
    --------------------------------------------------------------------------------------------------------------------------
    Define Entity               Class                                              Table Schema                 @Entity
                                                                                                                @Table
    Entity                      object                                             Record/Row/Ruple
    Field                       field/data member                                  Attribute/Column/Field       @Column
                                                                                                                @Transient
                                                                                                                @Id
    Behaviour                   method                                             -----------

    IS A
        Generalization          Inheretence                                                                     @Inheretence
                                                                                                                @DiscriminatorColumn
                                Employee (empId,name,sal)                                                       @DiscriminatorValue
                                 ↑
                                 --------------------------------
                                 ↑                              ↑
                                 ContractEmployee (duration)    Manager (allowence)

                                                                                   SINGLE TABLE
                                                                                    all_emps 
                                                                                        empId,name,sal,dur,allw,etype

                                                                                   JOIN TABLE
                                                                                    emps    empId,name,sal
                                                                                    mgrs    empId,allw
                                                                                    cemps   empId,dur

                                                                                   TABLE PER CLASS 
                                                                                    emps    empId,name,sal
                                                                                    mgrs    empId,name,sal,allw
                                                                                    cemps   empId,name,sal,dur

    HAS A / Association
        Composition                                                                                             @Embadable
                                                                                                                @Embeded
                                class Employee {                                    emps    empId,fnm,sal,doj,dno,al,st,city,ctr
                                    Long empId                                      cons    conId,fnm,dno,al,st,city,ctr
                                    String fullName
                                    Double salary
                                    LocalDate dateOfJoining
                                    Address address
                                }

                                class Consumer {
                                    Long conId
                                    String fullName
                                    Address address
                                }

                                class Address {
                                    String doorNo
                                    String azddressLine
                                    String city
                                    String state
                                    String country
                                }
        Aggregation
            OneToOne                                                                                        @OneToOne
                                                                                                            @JoinColumn
                                class Employee {                                   emps empId,fnm,sal
                                    Long empId                                     accs aNum,ifsc,empId(FK)
                                    String fullName
                                    Double salary
                                    BankAccount salAccount
                                }
                                                                                   emps empId,fnm,sal,aNum(FK)
                                class BankAccount {                                accs aNum,ifsc
                                    Long accNum
                                    String IFSCCode
                                    Employee holder
                                }

            OneToMany                                                                                       @OneToMany
            ManyToOne                                                                                       @ManyToOne
                                class Employee {                                    
                                    Long empId                                     emps empId,fnm,sal,deptId(FK)
                                    String fullName                                depts deptId,title
                                    Double salary
                                    Department dept
                                }

                                class Department {
                                   Long depId
                                   String title
                                   Set<Employee> emps
                                }

            ManytoMany                                                                                      @ManyToMany
                                                                                                            @JoinTable

                                class Artist {                                  ats     aid,fnm
                                    Long aid                                    mvs     mid,title
                                    String fullName                             ats_mvs aid,mid
                                    Set<Movie> movies
                                }                  

                                class Movie {
                                    Long mid
                                    String title
                                    Set<Artist> cast
                                }  

                                class Artist {                                  ats     aid,fnm
                                    Long aid                                    mvs     mid,title
                                    String fullName                             acts    aid,mid,chart
                                    Set<Act> acts
                                }                  

                                class Act {
                                    Movie movie
                                    Artist artist
                                    String character
                                    double remunaration
                                }  

                                class Movie {
                                    Long mid
                                    String title
                                    Set<Act> acts
                                }

                                class Employee {
                                    Long empId
                                    String fullName
                                    Set<Contribution> contributions
                                }

                                class Contribution {
                                    Employee emp
                                    Project proj
                                    String role
                                    LocalDate startDate
                                    LocalDate endDate
                                }

                                class Project {
                                    Long prjId
                                    String title
                                    Set<Contribution> contributions
                                }

    Lab SetUp

        MySQL           for RDBMS
        JDK 8           for OOP
        Eclipse/STS     for IDE

    Java Approach to ORM - JPA (Java Persistence API) + JTA (Java Transaction API)
    ------------------------------------------------------------------------

            specifications                      implementations
            JDBC                                database drivers
            Servlets API                        web servers like CATALINA of Tomcat
            JPA and JTA                         thrid party implmentors like
                                                        Hibernate
                                                        TopLink
                                                        Eclipse ORM ....etc

        JPA Configuaration              ${project.basedir}/src/META-INF/persistence.xml
            1. db driver
            2. db dialect
            3. db url, uid and pwds
            4. jpa provider (jpa implmentation)
            5. any other jpa provider related customizations....

        JPA Mapping Annotations

            @Entity             class level
            @Embedable          class level
            @Table              class level

            @Id                 field level     marks primary key
            @GeneratedValue     field level     strategy 'AUTO/INCREMENT/SEQUENCE'
            @Column             field level     name,unique,nullable ..etc
            @Transiant          field level     skip a field from being mapped with any column
            @Enumerated         field level     
            @EmbededId          field level     used to mark composite keys

                                                @Embedable
                                                class StudentId {
                                                    private Integer roll;
                                                    private String clazz;
                                                    private String section;
                                                }

                                                @Entity
                                                class Student{
                                                    @EmbededId
                                                    private StudentId studentId;
                                                    ...............
                                                }

            @Inheretence        class level     strategy 'SINGLE_TABLE/JOINED_TABLE/TABLE_PER_CLASS'
            @DiscriminatorColumn
            @DiscriminatorValue

            @Embeded            field level     for composition

            @OneToOne           field level     for one to one aggregation
            @OneToMany          field level     for one to many aggregation
            @ManyToOne          field level     for many to one aggregation
            @ManyToMany         field level     for many to many aggregation

            @JoinColumn         field level
            @JoinTable          field level


        Fetch Strategies
            LAZY
            EAGER

        Cascading Strategies
            NONE
            ALL
            PERSIST
            MERGE
            DELETE
            DETACH
            REFRESH

        Case Study
        ----------------------------------------------------------------------------------

            A Direct-to-Home (tatasky or dish...etc) System

            1. A consuemr is represented by his mobile number.
            2. While registration we need to record the name,address and mobile number
            3. Each Channel is represented by a channel code and detials like channel name
                monthly fee are needed
            4. A consumer can have more than one subscription
            5. Each subscription can have a many channels
            6. Each subscription will have a activation date and validity date.
            
            Consumer
                ---OneTOMany-->> <<--ManyToOne--- Subscription

            Channel
                ---OneTOMany-->> <<--ManyToOne--- SubscriptionItem

            Subscription
                ---OneTOMany-->> <<--ManyToOne--- SubscriptionItem

            Consumer
                mobileNumber:String, name:string,address:Address,subscriptions:Set<Subscrption>

            Channel
                chId:Long,channelName,monthlyFee,subsciptionItems:Set<SubsctiptionItem>

            Subscription
                subId:Long,consumer:Consumer,subsciptionItems:Set<SubsctiptionItem>,activationDate:LocalDate,expiryDate:LocalDate

            SubscriptionItem
                supItemId:Long,channel:Channel,subscription:Subsciption

        JPA api
            Persistence
                .createEntityManagerFactory("PU_NAME")
                    EntityManagerFactory
                        ::createEntityManager()
                            EntityManager
                                ::find(Entity.class,ID_VALUE) : Entity
                                ::persist(entity) : Entity                  INSERT
                                ::merge(entity)   : Entity                  UPDATE
                                ::remove(entity)  : void                    DELETE
                                ::createQuery("JPQL QRY") : Query
                                ::createQuery("JPQL QRY",Entity.class) : TypedQuery
                                ::getTransaction() : EntityTransaction
            
            EntityTransaction
                ::begin()
                ::commit()
                ::rollback()

            Query
                ::getResults() : List<Object[]>
                ::getStream() : Stream<Object[]>
            
            TypedQuery
                ::getResults() : List<Entity>
                ::getStream() : Stream<Entity>

        Hibernate api
            SessionFactory
                .createSession()
                    Session
                        ::get(entity.class,id_value) : Entity
                        ::load(entity.class,id_value) : Entity
                        ::save(entity)
                        ::update(entity)
                        ::saveOrUpdate(entity)
                        ::delete(entity)
                        ::createQuery("HQL QRY") : Query
                        ::createQuery("HQL QRY",Entity.class) : TypedQuery
                        ::beginTransaction() : Transaction

            hibernate.cfg.xml

            <?xml version="1.0" encoding="UTF-8"?>
            <!DOCTYPE hibernate-configuration PUBLIC 
            "-//Hibernate/Hibernate Configuration DTD 3.0//EN" 
            "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
            <hibernate-configuration>
                <session-factory>
                <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
                <property name="connection.url">jdbc:mysql://localhost:3306/jpaHibDb?createDatabaseIfNotExist=true</property>
                <property name="connection.username">root</property>
                <property name="connection.password">password</property>
                <property name="connection.pool_size">3</property>
                <property name="dialect">org.hibernate.dialect.MySQL8Dialect</property>
                <property name="current_session_context_class">thread</property>
                <property name="show_sql">true</property>
                <property name="format_sql">true</property>
                <property name="hbm2ddl.auto">update</property>
                
            </session-factory>
            </hibernate-configuration>

            // SessionFactory in Hibernate 5 example
            Configuration config = new Configuration();
            config.configure();
            // local SessionFactory bean created
            SessionFactory sessionFactory = config.buildSessionFactory();
            Session session = sessionFactory.getCurrentSession();

        JPQL - Java Persistence Query Language                     SQL
            uses class names and field names                            uses table names and column names

            SELECT e FROM Employee e                                    SELECT * FROM emps
            SELECT e.name,e.mailId FROM Employee e                      SELECT ename,mail_id FROM emps
            
            SELECT e.salary FROM Employee e                             SELECT e.sal 
            WHERE e.dept.title="ACCOUNTS"                               FROM   emps e INNET JOIN depts d 
                                                                        ON     e.dept_no = d.dept_no
                                                                        WHERE  d.title='ACCOUNTS'