// Importações de bibliotecas
const express = require('express');
const helmet = require('helmet');
const rateLimit = require('express-rate-limit');
const { buscarPizzaPeloNome } = require('./dbService'); // Serviço de Banco
const { verificarToken, gerarToken } = require('./authService'); // Serviço de Auth

const app = express();

// 1. SEGURANÇA: Cabeçalhos HTTP
app.use(helmet());

// 2. SEGURANÇA: Prevenção contra Brute Force
const limiter = rateLimit({
    windowMs: 15 * 60 * 1000,
    max: 100,
    message: "Muitas requisições deste IP, tente novamente mais tarde."
});
app.use(limiter);

// Middleware para processar JSON com limite de tamanho
app.use(express.json({ limit: '10kb' }));

// --- ROTAS ---

// Rota pública: Não precisa de token
app.get('/pizza/:nome', async (req, res) => {
    try {
        const nomePizza = req.params.nome;
        const pizza = await buscarPizzaPeloNome(nomePizza);
        res.json(pizza);
    } catch (err) {
        console.error(err);
        res.status(500).send("Erro interno ao buscar pizza.");
    }
});

// Rota protegida: Exige o token JWT no cabeçalho 'authorization'
app.get('/admin/relatorio', verificarToken, (req, res) => {
    // Se o código chegou aqui, o token é válido
    res.json({
        mensagem: "Bem-vindo, admin!",
        dados: "Aqui está o faturamento acumulado: R$ 5.000,00"
    });
});

// Rota de Login (Exemplo para gerar um token)
app.post('/login', (req, res) => {
    // Aqui você validaria usuário e senha no banco!
    const usuarioSimulado = { id: 1, role: 'admin' };
    const token = gerarToken(usuarioSimulado);
    res.json({ token });
});

// --- SERVIDOR ---
const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Servidor seguro rodando na porta ${PORT}`);
});