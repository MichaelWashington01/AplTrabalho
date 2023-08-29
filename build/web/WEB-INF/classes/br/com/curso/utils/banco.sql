create table estado (
	idestado serial primary key,
	nomeestado varchar(100) not null,
	siglaestado varchar(2) not null

);

insert into estado(nomeestado, siglaestado)
values('São Paulo', 'SP');

select * from estado;


create table veiculo(
 idVeiculo serial primary key,
	marca varchar (100) not null,
	modelo varchar (100) not null
);
insert into veiculo( marca, modelo) values ('chevrolet','cruze turbo');




	
	create table imovel (
	idimovel serial primary key,
	descricao varchar(100) not null,
	endereco varchar(100) not null,
	valoraluguel numeric 
);

insert into imovel (descricao, endereco,valoraluguel) values ('CENTRO', 'JALES',1200);



create table cidade(
   idcidade serial PRIMARY key,
	nomecidade varchar (100) not null,
	situacao varchar (1) not null,
	idestado int not null,
	CONSTRAINT fk_estado FOREIGN key (idestado)REFERENCES estado(idestado)
);


INSERT into cidade(nomecidade,situacao,idestado) VALUES ('Fernandopolis','A',1);
INSERT into cidade(nomecidade,situacao,idestado) VALUES ('Jales','A',1);


select*from cidade;


create table despesa (
 
	iddespesa serial primary key,
	descricao varchar (100),
	datadocumento date not null,
	valordespesa numeric (15,2) not null,
	valorpago numeric (15,2),
	imagemdocumento varchar
);


insert into despesa(descricao, datadocumento, valordespesa, valorpago)
   values ('descricao', '2023-08-21', 20.5, 10.5);
   
   select * from despesa;