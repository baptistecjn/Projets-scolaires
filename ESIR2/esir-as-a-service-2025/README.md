# ESIR-as-a-Service-2025

Ce projet déploie une suite complète de services auto-hébergés utilisant une architecture micro-services avec **Docker**, orchestrée derrière un Reverse Proxy **Nginx** sécurisé.

## Architecture

Le projet repose sur une machine virtuelle unique hébergeant :
* **Frontend :** Nginx (Reverse Proxy) gérant la terminaison SSL et le routage.
* **Backend :** 8 services conteneurisés via Docker Compose.
* **Sécurité :** Fail2Ban pour la protection contre les intrusions et HTTPS forcé.
* **Monitoring :** Surveillance active via Gatus.

## État des Services

Tous les services sont accessibles en HTTPS via le domaine `baptiste.istic.univ-rennes1.fr`.

| Service | URL d'accès | Description | Statut |
| :--- | :--- | :--- | :--- |
| **Portail d'accueil** | `https://baptiste.istic.univ-rennes1.fr/` | Page statique listant les applications | Fonctionnel |
| **Nextcloud** | `https://baptiste.istic.univ-rennes1.fr/nextcloud/` | Stockage de fichiers et collaboration | Fonctionnel |
| **Rocket.Chat** | `https://baptiste.istic.univ-rennes1.fr/rocketchat/` | Messagerie instantanée d'équipe | Fonctionnel |
| **Gitea** | `https://baptiste.istic.univ-rennes1.fr/gitea/` | Gestion de code source | Fonctionnel |
| **WordPress** | `https://baptiste.istic.univ-rennes1.fr/wordpress/` | Blog | Fonctionnel |
| **KeePassXC** | `https://baptiste.istic.univ-rennes1.fr/keepass/` | Gestionnaire de mots de passe | Fonctionnel |
| **Memos** | `https://baptiste.istic.univ-rennes1.fr/memos/` | Prise de notes | Fonctionnel |
| **Adminer** | `https://baptiste.istic.univ-rennes1.fr/adminer/` | Gestionnaire de bases de données | Fonctionnel |
| **Gatus**| `https://gatus.baptiste.istic.univ-rennes1.fr/` | Tableau de surveillance | Fonctionnel |

## Installation et Démarrage

Voici comment redéployer l'infrastructure complète sur une nouvelle machine :

1. **Cloner le dépôt :**
   ```bash
   git clone [https://gitlab.istic.univ-rennes1.fr/bcojean/esir-as-a-service-2025.git](https://gitlab.istic.univ-rennes1.fr/bcojean/esir-as-a-service-2025.git)
   cd esir-as-a-service-2025
   ```
2. **Restaurer les configurations système :**
    Les fichiers de configuration Nginx et Fail2Ban sont sauvegardés dans le dossier config/.
    # Copie des configurations Nginx
    ```bash
    sudo cp config/nginx/reverse-proxy.conf /etc/nginx/sites-available/
    sudo ln -s /etc/nginx/sites-available/reverse-proxy.conf /etc/nginx/sites-enabled/
    sudo cp config/nginx/.htpasswd /etc/nginx/
    ```
    # Copie de la configuration Fail2Ban
    ```bash
    sudo cp config/fail2ban/jail.local /etc/fail2ban/
    ```

3. **Générer les certificats SSL (Self-Signed) :**
    ```bash
    sudo mkdir -p /etc/nginx/ssl
    sudo openssl req -x509 -nodes -days 365 -newkey rsa:2048 \
        -keyout /etc/nginx/ssl/nginx-selfsigned.key \
        -out /etc/nginx/ssl/nginx-selfsigned.crt
    ```
4. **Lancer les services :**
    ```bash
    sudo docker compose up -d
    sudo systemctl reload nginx
    sudo systemctl restart fail2ban
    ```

## Sécurité et Choix Techniques
* **Reverse Proxy Nginx :** Choisi pour sa légèreté et sa flexibilité par rapport à Traefik pour gérer des configurations spécifiques (Rewrite d'URL pour Gatus/RocketChat).
* **HTTPS intégral :** Utilisation de certificats auto-signés avec redirection forcée HTTP -> HTTPS.
* **Protection Fail2Ban :** Configuration d'une prison nginx-http-auth pour bannir les IP après 5 échecs de connexion sur les pages protégées (SSH et Web).
* **Double Authentification (Basic Auth) :** Ajoutée en surcouche sur l'administration WordPress et le portail d'accueil pour renforcer la sécurité.
* **Monitoring Actif (Gatus) :** Configuration "As Code" (YAML) préférée à une base de données pour faciliter le versionning et le déploiement automatique.

## Structure du projet

```text
.
├── config/                  # Fichiers de configuration système (Sauvegardés)
│   ├── fail2ban/
│   │   └── jail.local       # Règles de bannissement des IP
│   └── nginx/
│       ├── .htpasswd        # Mots de passe pour la Basic Auth
│       └── reverse-proxy.conf # Configuration du Reverse Proxy & SSL
├── gatus/                   # Configuration du monitoring
│   └── config.yaml          # Liste des services à surveiller
├── pages/                   # Site web statique (Portail d'accueil)
│   └── index.html
├── docker-compose.yml       # Orchestration de tous les conteneurs
└── README.md                # Documentation du projet
```

## Auteur

Baptiste COJEAN - ESIR 2 - Architecture Logicielle 2025
