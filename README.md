# jpa
jpa-study

# database
## create databse container
docker run -p 5435:5435 -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=springdata --name postgres_boot -d postgres
## excute databse container
docker exec -i -t postgres_boot bash
## access database
su - postgres
psql springdata
