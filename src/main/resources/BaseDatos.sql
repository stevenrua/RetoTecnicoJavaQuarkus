-- DROP SCHEMA dbo;

CREATE SCHEMA dbo;
-- panamabanco.dbo.Cliente definition

-- Drop table

-- DROP TABLE panamabanco.dbo.Cliente;

CREATE TABLE panamabanco.dbo.Cliente (
	id bigint NOT NULL,
	direccion varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	edad int NOT NULL,
	genero varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	identificacion varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	nombre varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	telefono varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	contrasena varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	estado bit NOT NULL,
	CONSTRAINT PK__Cliente__3213E83F7A35BE53 PRIMARY KEY (id)
);
 CREATE  UNIQUE NONCLUSTERED INDEX UK_4l7ftruxsjh5qf8hwftwt52rj ON dbo.Cliente (  identificacion ASC  )  
	 WHERE  ([identificacion] IS NOT NULL)
	 WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
	 ON [PRIMARY ] ;
ALTER TABLE panamabanco.dbo.Cliente WITH NOCHECK ADD CONSTRAINT CK__Cliente__genero__37A5467C CHECK ([genero]='m' OR [genero]='M' OR [genero]='f' OR [genero]='F');


-- panamabanco.dbo.hibernate_sequences definition

-- Drop table

-- DROP TABLE panamabanco.dbo.hibernate_sequences;

CREATE TABLE panamabanco.dbo.hibernate_sequences (
	sequence_name varchar(255) COLLATE Modern_Spanish_CI_AS NOT NULL,
	next_val bigint NULL,
	CONSTRAINT PK__hibernat__666199D5D5EB3329 PRIMARY KEY (sequence_name)
);


-- panamabanco.dbo.Cuenta definition

-- Drop table

-- DROP TABLE panamabanco.dbo.Cuenta;

CREATE TABLE panamabanco.dbo.Cuenta (
	id bigint IDENTITY(1,1) NOT NULL,
	estado bit NOT NULL,
	numeroCuenta int NULL,
	saldoInicial float NOT NULL,
	tipoCuenta varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	cliente_id bigint NULL,
	CONSTRAINT PK__Cuenta__3213E83FC2D8512B PRIMARY KEY (id),
	CONSTRAINT FKrjx80l1sk107ubydipkvslsas FOREIGN KEY (cliente_id) REFERENCES panamabanco.dbo.Cliente(id)
);
 CREATE  UNIQUE NONCLUSTERED INDEX UK_m09koyl5cv8p27c5gmrk7xk8u ON dbo.Cuenta (  numeroCuenta ASC  )  
	 WHERE  ([numeroCuenta] IS NOT NULL)
	 WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
	 ON [PRIMARY ] ;


-- panamabanco.dbo.Movimientos definition

-- Drop table

-- DROP TABLE panamabanco.dbo.Movimientos;

CREATE TABLE panamabanco.dbo.Movimientos (
	id bigint IDENTITY(1,1) NOT NULL,
	[date] date NULL,
	saldo float NOT NULL,
	tipoMovimiento varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	valor float NOT NULL,
	cuenta_id bigint NULL,
	CONSTRAINT PK__Movimien__3213E83F04C17A37 PRIMARY KEY (id),
	CONSTRAINT FKfupa9mswcej5ii8r5pet8joq1 FOREIGN KEY (cuenta_id) REFERENCES panamabanco.dbo.Cuenta(id)
);
ALTER TABLE panamabanco.dbo.Movimientos WITH NOCHECK ADD CONSTRAINT CK__Movimient__tipoM__3E52440B CHECK ([tipoMovimiento]='Retiro' OR [tipoMovimiento]='Deposito');


