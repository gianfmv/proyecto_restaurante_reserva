-- Crear base de datos
CREATE DATABASE Reserva;
GO

USE Reserva;
GO

-- Tabla: TipoUsuario
CREATE TABLE TipoUsuario (
    IdTipo INT PRIMARY KEY,
    Descripcion NVARCHAR(50) NOT NULL
);

INSERT INTO TipoUsuario VALUES (1, 'Administrador'), (2, 'Cliente');

-- Tabla: Usuario
CREATE TABLE Usuario (
    IdUsuario INT PRIMARY KEY IDENTITY(1,1),
    NombreCompleto NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) UNIQUE NOT NULL,
    Contraseña NVARCHAR(100) NOT NULL,
    IdTipo INT NOT NULL,
    Telefono NVARCHAR(20),
    DNI NVARCHAR(20),
    Direccion NVARCHAR(200),
    FOREIGN KEY (IdTipo) REFERENCES TipoUsuario(IdTipo)
);

-- Tabla: Mesas
CREATE TABLE Mesas (
    IdMesa INT PRIMARY KEY IDENTITY(1,1),
    Capacidad INT NOT NULL,
    Disponible BIT NOT NULL DEFAULT 1 -- 1 = disponible, 0 = ocupada
);

-- Tabla: Reservas
CREATE TABLE Reservas (
    IdReserva INT PRIMARY KEY IDENTITY,
    IdCliente INT NOT NULL,
    FechaReserva DATE NOT NULL,
    HoraReserva TIME NOT NULL, -- validación entre 12:00 y 22:00 en la app
    CantPersonas INT NOT NULL,
    Estado NVARCHAR(20) DEFAULT 'Pendiente',
    IdMesa INT NOT NULL,
    FOREIGN KEY (IdCliente) REFERENCES Usuario(IdUsuario),
    FOREIGN KEY (IdMesa) REFERENCES Mesas(IdMesa)
);

-- Tabla: Menus
CREATE TABLE Menus (
    IdMenu INT PRIMARY KEY IDENTITY,
    Nombre NVARCHAR(100) NOT NULL,
    Descripcion NVARCHAR(255),
    Precio DECIMAL(10,2) NOT NULL
);

-- Tabla: Ingredientes
CREATE TABLE Ingredientes (
    IdIngrediente INT PRIMARY KEY IDENTITY,
    Nombre NVARCHAR(100) NOT NULL,
    EsAlergeno BIT NOT NULL -- 1 = alérgeno
);

-- Tabla: Menu_Ingredientes
CREATE TABLE Menu_Ingredientes (
    IdMenu INT,
    IdIngrediente INT,
    PRIMARY KEY (IdMenu, IdIngrediente),
    FOREIGN KEY (IdMenu) REFERENCES Menus(IdMenu),
    FOREIGN KEY (IdIngrediente) REFERENCES Ingredientes(IdIngrediente)
);

-- Tabla: Pedidos
CREATE TABLE Pedidos (
    IdPedido INT PRIMARY KEY IDENTITY,
    IdReserva INT NOT NULL,
    FechaPedido DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (IdReserva) REFERENCES Reservas(IdReserva)
);

-- Tabla: Pedido_Menu
CREATE TABLE Pedido_Menu (
    IdPedido INT,
    IdMenu INT,
    Cantidad INT NOT NULL,
    PRIMARY KEY (IdPedido, IdMenu),
    FOREIGN KEY (IdPedido) REFERENCES Pedidos(IdPedido),
    FOREIGN KEY (IdMenu) REFERENCES Menus(IdMenu)
);

-- Tabla: Detalle_Alergias
CREATE TABLE Detalle_Alergias (
    IdUsuario INT,
    IdIngrediente INT,
    PRIMARY KEY (IdUsuario, IdIngrediente),
    FOREIGN KEY (IdUsuario) REFERENCES Usuario(IdUsuario),
    FOREIGN KEY (IdIngrediente) REFERENCES Ingredientes(IdIngrediente)
);

-- Tabla: Promociones
CREATE TABLE Promociones (
    IdPromocion INT PRIMARY KEY IDENTITY,
    Titulo NVARCHAR(100) NOT NULL,
    Descripcion NVARCHAR(255),
    FechaInicio DATE NOT NULL,
    FechaFin DATE NOT NULL,
    DescuentoPorcentaje DECIMAL(5,2) NOT NULL
);
