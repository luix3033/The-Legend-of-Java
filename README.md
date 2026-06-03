# 🗡️ The Legend of Java

Um jogo 2D inspirado na franquia The Legend of Zelda, desenvolvido em Java utilizando apenas as bibliotecas padrão da linguagem e renderização manual com Canvas e BufferStrategy.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

---

## 🎮 Sobre o Projeto

The Legend of Java é um projeto criado com fins de estudo para aprofundar conhecimentos em:

- Programação Orientada a Objetos (POO)
- Desenvolvimento de jogos 2D
- Sistemas de colisão
- Inteligência Artificial básica
- Gerenciamento de sprites
- Estruturação de projetos Java
- Manipulação de mapas por imagens

O objetivo é recriar mecânicas clássicas dos jogos Zelda utilizando apenas Java.

---

## 📸 Funcionalidades

### ✅ Implementadas

- Movimentação em 4 direções
- Sistema de animações
- Colisão com paredes
- Câmera dinâmica
- Sistema de vida
- HUD de vida
- Sistema de munição
- Arco e flecha
- Tiro em direção ao mouse
- Inimigos Goblin
- Dano ao jogador
- Sistema de fases
- Itens coletáveis
  - Arco
  - Flechas
  - Kits de vida
- Game Over
- - Menu principal
  - - Efeitos sonoros
- Música de fundo

### 🚧 Em Desenvolvimento

- Raio de detecção dos inimigos
- Sistema de pontuação
- Novos inimigos
- Chefões
- Sistema de save

---

## 🕹️ Controles

| Tecla | Ação |
|---------|---------|
| W | Mover para cima |
| A | Mover para esquerda |
| S | Mover para baixo |
| D | Mover para direita |
| X | Atirar |
| Mouse | Mirar e atirar |

---

## 🗺️ Sistema de Mapas

Os mapas são carregados através de imagens PNG.

Cada cor representa um elemento diferente do jogo:

| Cor | Objeto |
|------|---------|
| Preto | Chão |
| Branco | Parede |
| Azul | Spawn do jogador |
| Vermelho | Goblin |
| Verde | Kit de Vida |
| Amarelo | Flechas |
| Laranja | Arco |

---

## 🏗️ Estrutura do Projeto

```text
src/
│
├── main/
│   └── Game.java
│
├── entities/
│   ├── Player.java
│   ├── Goblin.java
│   ├── ArrowShoot.java
│   ├── Weapon.java
│   └── ...
│
├── world/
│   ├── World.java
│   ├── Tile.java
│   ├── Camera.java
│   └── ...
│
└── graficos/
    ├── Spritesheet.java
    └── UI.java
```

---

## ⚙️ Tecnologias Utilizadas

- Java
- Java AWT
- Java Swing
- BufferStrategy
- BufferedImage

---

## 🚀 Como Executar

### Clone o repositório

```bash
git clone https://github.com/seuusuario/the-legend-of-java.git
```

### Abra no Eclipse ou IntelliJ

Importe o projeto como Java Project.

### Execute

```bash
Game.java
```

---

## 📚 O que aprendi com este projeto

Durante o desenvolvimento deste jogo foram aplicados conceitos como:

- POO
- Herança
- Polimorfismo
- Game Loop
- Spritesheets
- Detecção de colisão
- IA básica de perseguição
- Gerenciamento de estados do jogo
- Renderização em tempo real

---

## 🎯 Próximos Objetivos

- [ ] Sistema de inventário
- [ ] NPCs
- [ ] Missões
- [ ] Boss Fight
- [x] Sons e músicas
- [x] Tela de menu
- [ ] Sistema de save/load

---

## 👨‍💻 Autor

**Luis Ricardo**

Estudante de SIstemas e desenvolvedor Java.

GitHub: https://github.com/luix3033

---

## ⭐ Se gostou do projeto

Deixe uma estrela no repositório para apoiar o desenvolvimento!
