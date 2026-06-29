// Importando bibliotecas necessárias
const express = require('express');
const helmet = require('helmet'); // Protege com headers HTTP seguros
const rateLimit = require('express-rate-limit'); // Limita o número de requisições

const app = express();

// 1. APLICAÇÃO DO HELMET
// Define vários cabeçalhos HTTP de segurança automaticamente
app.use(helmet());

// 2. CONFIGURAÇÃO DE RATE LIMIT
// Previne ataques de força bruta (Brute Force) ou DoS
const limiter = rateLimit({
    windowMs: 15 * 60 * 1000, // 15 minutos de intervalo
    max: 100, // Limita a 100 requisições por IP neste intervalo
    message: "Muitas requisições deste IP, tente novamente mais tarde."
});

// Aplica o limite globalmente em todas as rotas
app.use(limiter);

// Middleware para permitir JSON (segurança básica de entrada)
app.use(express.json({ limit: '10kb' })); // Limita o tamanho do JSON para evitar sobrecarga

// Rota de exemplo
app.get('/', (req, res) => {
    res.send('Servidor Node.js protegido!');
});

const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Servidor seguro rodando na porta ${PORT}`);
});