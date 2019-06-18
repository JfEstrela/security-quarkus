# security-quarkus

## Creating the Maven project Quarkus
First, we need a new project. Create a new project with the following command

```
mvn io.quarkus:quarkus-maven-plugin:0.16.1:create \
    -DprojectGroupId=org.acme \
    -DprojectArtifactId=rest-json \
    -DclassName="org.acme.rest.json.FruitResource" \
    -Dpath="/fruits" \
    -Dextensions="resteasy-jsonb"
    
```

## Running your project Quarkus

```
./mvnw compile quarkus:dev:

```

## EndPoints

### EndPoint Account
```java

    @Inject
    private SecurityService service;

    @GET
    @Path("/")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response hello() {
        return Response.ok(getGenericEntity(service.account())).build();
    }
    
```

### EndPoint Details Account

```java

    @Inject
    private SecurityService service;
    
    @GET
    @Path("/details")
    @RolesAllowed("USER") 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response helloApresentation() {
      return Response.ok(getGenericEntity(service.accountDetails())).build();
    }
    
```
### EndPoint Details Account full

```java

    @Inject
    private SecurityService service;	
    
    @GET
    @Path("/details/full")
    @RolesAllowed("ADMIN") 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response greeting() {
        return Response.ok(getGenericEntity(service.accountDetailsFull())).build();
    }
    
```

#### SecurityService

```java

@ApplicationScoped
public class SecurityService {
	
	@ConfigProperty(name = "security.account.name")
	private String nameAccount;
	
	@ConfigProperty(name = "security.account")
	private String account;
	
	@ConfigProperty(name = "security.account.agency")
	private String agency;
	
	@ConfigProperty(name = "security.account.password")
	private String password;

	
	public AccountDetails account() {
        return new AccountDetails(account);
    }
	
	public AccountDetails accountDetails() {
		return   new AccountDetails(account,agency,nameAccount);
	}
	
	public AccountDetails accountDetailsFull() {
		return new AccountDetails(account,agency,password,nameAccount);
	}

}

  
```
### AccountDetails

```java
@RegisterForReflection
public class AccountDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String account;
	private String agency;
	private String password;
	private String  nameAccount;
	
	public  AccountDetails(String account) {
		this.account = account;
	}
	
	public  AccountDetails(String account,String agency,String nameAccount) {
		this(account);
		this.agency = agency;
		this.nameAccount = nameAccount;
	}
	
	public  AccountDetails(String account,String agency, String password,String nameAccount) {
		this(account, agency, nameAccount);
		this.nameAccount = nameAccount;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNameAccount() {
		return nameAccount;
	}

	public void setNameAccount(String nameAccount) {
		this.nameAccount = nameAccount;
	}
}

```
### application

```properties
# Configuration file
# key = value

quarkus.security.file.enabled=true
quarkus.security.file.users=security-users.properties
quarkus.security.file.roles=security-roles.properties
quarkus.security.file.auth-mechanism=BASIC
quarkus.security.file.realm-name=MyRealm

security.account.name=José Fernando de Carvalho Estrela
security.account=661224
security.account.agency=2
security.account.password=try1your2luck

```
## security-users.properties

```properties
admin=nimda
user=resu
admuser=resumda

```

## security-roles.properties

```properties
admin=ADMIN
user=USER
admuser=ADMIN,USER
```
## Reference Documentation
For further reference, please consider the following sections:

* [SECURITY GUIDE](https://quarkus.io/guides/security-guide)
* [REST JSON GUIDE](https://quarkus.io/guides/rest-json-guide)






