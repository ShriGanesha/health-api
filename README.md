

**Cassandra**

Create keyspace: 
create keyspace if not exists ehr_db with replication = { 'class':'SimpleStrategy', 'replication_factor':1};

Create table: 
create table if not exists patients (
mrn_id text primary key,
document text,
created_at timestamp,
updated_at timestamp
);
