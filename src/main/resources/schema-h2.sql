CREATE TABLE payments(
    id BIGINT primary key auto_increment,
    contractId int,
    description varchar(50),
    value int,
    time timestamp,
    isImported boolean,
    createdAt timestamp,
    updatedAt timestamp,
    isDeleted boolean);
