/* /*CREATE TABLE Usuario (
    idUsuario INTEGER PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL
);

CREATE TABLE Cliente (
    cpf INTEGER PRIMARY KEY,
    dataNascimento INTEGER NOT NULL, 
    telefone VARCHAR(15) NOT NULL
    historicoDeCompra 
   
);

CREATE TABLE Organizador (
    cnpj VARCHAR PRIMARY KEY,
    nomeDaEmpresa VARCHAR NOT NULL, 
    telefoneComercial VARCHAR(15) NOT NULL
    eventosCrados
   
);

CREATE TABLE Evento (
    idEvento INTEGER PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    dataHoraInicio TIMESTAMP NOT NULL,
    dataHoraFim TIMESTAMP NOT NULL,
    local VARCHAR(100) NOT NULL
    statusEvento VARCHAR(100) NOT NULL CHECK (statusEvento IN ('AGENDADO','EM_ANDAMENTO', 'REALIZADO', 'CANCELADO', 'ADIADO', 'VENDAS_ABERTAS')), 
    capacidadeMaximaPublico INTEGER NOT NULL
);

CREATE TABLE Ingresso (
    idIngresso INTEGER PRIMARY KEY,
    dataHoraVenda TIME NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    statusIngresso VARCHAR(100) NOT NULL CHECK (statusIngresso IN ('DISPONIVEL', 'VENDIDO', 'RESERVADO'))
);

CREATE TABLE Venda (
    idVenda INTEGER PRIMARY KEY,
    dataHoraVenda TIMESTAMP NOT NULL,
    valorTotal FLOAT NOT NULL,
    formaPagamento VARCHAR(100) NOT NULL,
    statusVenda VARCHAR(100) NOT NULL
);

CREATE TABLE categoriaIngresso (
    idCategoria INTEGER PRIMARY KEY,
    nomeCategoria VARCHAR NOT NULL,
    precoBase DOUBLE NOT NULL
);

CREATE TABLE Comprovante(
    idComprovante VARCHAR PRIMARY KEY,
    vendaAssociada INTEGER NOT NULL,
    dataEmissao TIMESTAMP NOT NULL,
    detalhesTransacao VARCHAR NOT NULL,
    codigoAutenticacao VARCHAR NOT NULL
);

CREATE TABLE loteIngresso(
    idLote INTEGER PRIMARY KEY,
    preco DOUBLE NOT NULL,
    quantidadeTotalDisponivel INTEGER NOT NULL,
    quantidadeVendida INTEGER NOT NULL
);*/

-- Tabela para todos os tipos de utilizadores do sistema.
-- Contém as informações básicas de login.
CREATE TABLE Usuario (
    idUsuario INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    senha TEXT NOT NULL
);

-- Tabela Cliente, que é um tipo de Usuario.
-- Armazena dados específicos do cliente, como CPF.
-- Relaciona-se com Usuario através de uma chave estrangeira.
CREATE TABLE Cliente (
    cpf TEXT PRIMARY KEY,
    idUsuario INTEGER NOT NULL UNIQUE,
    dataNascimento TEXT NOT NULL,
    telefone TEXT(15) NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);

-- Tabela Organizador, que também é um tipo de Usuario.
-- Armazena dados da empresa que organiza os eventos.
CREATE TABLE Organizador (
    cnpj TEXT PRIMARY KEY,
    idUsuario INTEGER NOT NULL UNIQUE,
    nomeDaEmpresa TEXT NOT NULL,
    telefoneComercial TEXT(15) NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);

-- Tabela principal de Eventos.
-- Cada evento é criado por um Organizador.
CREATE TABLE Evento (
    idEvento INTEGER PRIMARY KEY AUTOINCREMENT,
    cnpjOrganizador TEXT NOT NULL,
    nome TEXT NOT NULL,
    descricao TEXT NOT NULL,
    dataHoraInicio TEXT NOT NULL, -- Formato recomendado: 'YYYY-MM-DD HH:MM:SS'
    dataHoraFim TEXT NOT NULL,    -- Formato recomendado: 'YYYY-MM-DD HH:MM:SS'
    local TEXT NOT NULL,
    statusEvento TEXT NOT NULL CHECK (statusEvento IN ('AGENDADO', 'EM_ANDAMENTO', 'REALIZADO', 'CANCELADO', 'ADIADO', 'VENDAS_ABERTAS')),
    capacidadeMaximaPublico INTEGER NOT NULL,
    FOREIGN KEY (cnpjOrganizador) REFERENCES Organizador(cnpj)
);

-- Tabela para os tipos de ingresso (ex: Pista, VIP, Camarote).
-- Cada categoria pertence a um evento específico.
CREATE TABLE CategoriaIngresso (
    idCategoria INTEGER PRIMARY KEY AUTOINCREMENT,
    idEvento INTEGER NOT NULL,
    nomeCategoria TEXT NOT NULL,
    precoBase REAL NOT NULL,
    FOREIGN KEY (idEvento) REFERENCES Evento(idEvento)
);

-- Tabela para os lotes de ingressos.
-- Um lote pertence a um evento e define um preço e quantidade.
CREATE TABLE LoteIngresso(
    idLote INTEGER PRIMARY KEY AUTOINCREMENT,
    idEvento INTEGER NOT NULL,
    preco REAL NOT NULL,
    quantidadeTotalDisponivel INTEGER NOT NULL,
    quantidadeVendida INTEGER NOT NULL DEFAULT 0, -- Começa com 0 vendidos
    idCategoria INTEGER NOT NULL, -- Adicionado para refletir a classe
    FOREIGN KEY (idCategoria) REFERENCES CategoriaIngresso(idCategoria)
    FOREIGN KEY (idEvento) REFERENCES Evento(idEvento)
);

-- Tabela que representa uma venda.
-- Uma venda é feita por um Cliente.
CREATE TABLE Venda (
    idVenda INTEGER PRIMARY KEY AUTOINCREMENT,
    cpfCliente TEXT NOT NULL,
    dataHoraVenda TEXT NOT NULL, -- Formato: 'YYYY-MM-DD HH:MM:SS'
    valorTotal REAL NOT NULL,
    formaPagamento TEXT NOT NULL,
    statusVenda TEXT NOT NULL CHECK (statusVenda IN ('APROVADA', 'PENDENTE', 'CANCELADA', 'REEMBOLSADA')),
    FOREIGN KEY (cpfCliente) REFERENCES Cliente(cpf)
);

-- Tabela para cada ingresso individual.
-- Um ingresso pertence a um Evento, a uma Venda, a uma Categoria e a um Lote.
CREATE TABLE Ingresso (
    idIngresso INTEGER PRIMARY KEY AUTOINCREMENT,
    idVenda INTEGER NOT NULL,
    idEvento INTEGER NOT NULL,
    idCategoria INTEGER NOT NULL,
    idLote INTEGER NOT NULL,
    preco REAL NOT NULL,
    statusIngresso TEXT NOT NULL CHECK (statusIngresso IN ('VALIDO', 'UTILIZADO', 'CANCELADO')),
    FOREIGN KEY (idVenda) REFERENCES Venda(idVenda),
    FOREIGN KEY (idEvento) REFERENCES Evento(idEvento),
    FOREIGN KEY (idCategoria) REFERENCES CategoriaIngresso(idCategoria),
    FOREIGN KEY (idLote) REFERENCES LoteIngresso(idLote)
);

-- Tabela para o comprovativo da transação de uma venda.
CREATE TABLE Comprovante(
    idComprovante TEXT PRIMARY KEY, -- Pode ser um UUID gerado pela aplicação
    idVenda INTEGER NOT NULL,
    dataEmissao TEXT NOT NULL,
    detalhesTransacao TEXT NOT NULL,
    codigoAutenticacao TEXT NOT NULL,
    FOREIGN KEY (idVenda) REFERENCES Venda(idVenda)
); */