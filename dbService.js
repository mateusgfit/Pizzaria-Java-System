const express = require('express');
const helmet = require('helmet');
const rateLimit = require('express-rate-limit');
// Importa a função que criamos para buscar no banco
const { buscarPizzaPeloNome } = require('./dbService');

const app = express();

app.use(helmet());

const limiter = rateLimit({
    windowMs: 15 * 60 * 1000,
    max: 100,
    message: "Muitas requisições deste IP, tente novamente mais tarde."
});

app.use(limiter);
app.use(express.json({ limit: '10kb' }));

// --- COLE O CÓDIGO DA ROTA AQUI ---
app.get('/pizza/:nome', async (req, res) => {
    try {
        const nomePizza = req.params.nome;
        const pizza = await buscarPizzaPeloNome(nomePizza);
        res.json(pizza);
    } catch (err) {
        // Log do erro para você ver no terminal, mas enviamos mensagem genérica ao cliente
        console.error(err);
        res.status(500).send("Erro interno ao buscar pizza.");
    }
});
// ----------------------------------

const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Servidor seguro rodando na porta ${PORT}`);
});