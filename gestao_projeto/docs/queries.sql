create database gestao_projetos;
use gestao_projetos;

CREATE TABLE Projeto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,        
    descricao TEXT,                    
    dataInicio DATE,                   
    dataFim DATE,                     
    responsavel INT,                    
    FOREIGN KEY (responsavel) REFERENCES Responsavel(id)
);

CREATE TABLE Responsavel (
    id INT AUTO_INCREMENT PRIMARY KEY,   
    nome VARCHAR(255) NOT NULL,          
    email VARCHAR(255) UNIQUE,            
    telefone VARCHAR(20),                
    cargo VARCHAR(100)             
);

CREATE TABLE Tarefa (
    id INT AUTO_INCREMENT PRIMARY KEY,     
    projetoId INT,                         
    nome VARCHAR(255) NOT NULL,            
    descricao TEXT,                        
    dataInicio DATE,                        
    dataFim DATE,                           
    responsavel INT,                       
    estado VARCHAR(50) DEFAULT 'Pendente',  
    FOREIGN KEY (projetoId) REFERENCES Projeto(id),  
    FOREIGN KEY (responsavel) REFERENCES Responsavel(id)  
);

-- Inserindo responsáveis
INSERT INTO Responsavel (nome, email, telefone, cargo) 
VALUES ('João Silva', 'joao.silva@email.com', '123-456-7890', 'Gerente de Projetos');

INSERT INTO Responsavel (nome, email, telefone, cargo) 
VALUES ('Maria Oliveira', 'maria.oliveira@email.com', '234-567-8901', 'Coordenadora de Equipe');

INSERT INTO Responsavel (nome, email, telefone, cargo) 
VALUES ('Carlos Pereira', 'carlos.pereira@email.com', '345-678-9012', 'Desenvolvedor Líder');

INSERT INTO Responsavel (nome, email, telefone, cargo) 
VALUES ('Fernanda Costa', 'fernanda.costa@email.com', '456-789-0123', 'Analista de Sistemas');


-- Inserindo projetos
INSERT INTO Projeto (nome, descricao, dataInicio, dataFim, responsavel)
VALUES ('InovaTech 2024', 'Desenvolvimento de uma plataforma inteligente de automação para empresas de tecnologia', '2024-01-01', '2024-06-30', 1); -- Responsável: João Silva

INSERT INTO Projeto (nome, descricao, dataInicio, dataFim, responsavel)
VALUES ('GreenTech Solutions', 'Criação de soluções sustentáveis e eficientes para o setor de energia renovável', '2024-03-15', '2024-09-15', 2); -- Responsável: Maria Oliveira

INSERT INTO Projeto (nome, descricao, dataInicio, dataFim, responsavel)
VALUES ('CyberShield', 'Desenvolvimento de uma infraestrutura de segurança cibernética para empresas de grande porte', '2024-05-01', '2024-11-30', 3); -- Responsável: Carlos Pereira

INSERT INTO Projeto (nome, descricao, dataInicio, dataFim, responsavel)
VALUES ('DataVision 360', 'Implementação de ferramentas avançadas de análise de dados para otimizar a tomada de decisões empresariais', '2024-07-01', '2024-12-31', 4); -- Responsável: Fernanda Costa

-- Inserindo tarefas
INSERT INTO Tarefa (projetoId, nome, descricao, dataInicio, dataFim, responsavel, estado)
VALUES (1, 'Desenvolvimento de Sistema', 'Desenvolvimento de um sistema de gestão', '2024-12-01', '2024-12-31', 1, 'Pendente');

INSERT INTO Tarefa (projetoId, nome, descricao, dataInicio, dataFim, responsavel, estado)
VALUES (2, 'Planejamento de Marketing', 'Elaboração de plano de marketing para o novo produto', '2024-12-05', '2024-12-20', 2, 'Pendente');

INSERT INTO Tarefa (projetoId, nome, descricao, dataInicio, dataFim, responsavel, estado)
VALUES (3, 'Teste de Software', 'Testes de unidade e integração do sistema', '2024-12-10', '2024-12-15', 3, 'Pendente');

INSERT INTO Tarefa (projetoId, nome, descricao, dataInicio, dataFim, responsavel, estado)
VALUES (4, 'Revisão de Documentação', 'Revisão e correção de documentação técnica', '2024-12-12', '2024-12-20', 4, 'Pendente');


select *from Tarefa;