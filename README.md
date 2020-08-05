# jpa

## database
1. create databse container   
```docker run -p 5435:5435 -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=springdata --name postgres_boot -p 25432:5432 -d postgres```

2. excute databse container   
```docker exec -i -t postgres_boot bash```

3. access database   
```su - postgres```   
```psql springdata```

4. create table   
