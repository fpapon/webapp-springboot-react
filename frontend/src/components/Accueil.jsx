import { useEffect, useState } from 'react';
import { commandeService } from '../services/commandeService';
import './Accueil.css';

function Accueil() {
  const [commandes, setCommandes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchCommandes = async () => {
      try {
        setLoading(true);
        const data = await commandeService.getAllCommandes();
        setCommandes(data);
      } catch (err) {
        setError('Erreur lors du chargement des commandes : ' + err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchCommandes();
  }, []);

  const formatMontant = (montant) =>
    new Intl.NumberFormat('fr-FR', {
      style: 'currency',
      currency: 'EUR',
    }).format(montant);

  const formatDate = (date) =>
    new Date(date).toLocaleDateString('fr-FR');

  const getStatutClass = (statut) => {
    const classes = {
      LIVREE: 'statut-livree',
      EN_COURS: 'statut-en-cours',
      VALIDEE: 'statut-validee',
      EN_ATTENTE: 'statut-en-attente',
    };
    return classes[statut] || '';
  };

  if (loading) return <div className="loader">Chargement des commandes...</div>;
  if (error) return <div className="error">{error}</div>;

  return (
    <div className="accueil">
      <header className="accueil-header">
        <h1>Liste des commandes</h1>
        <p>{commandes.length} commande(s) trouvée(s)</p>
      </header>

      <div className="table-container">
        <table className="commandes-table">
          <thead>
            <tr>
              <th>N° Commande</th>
              <th>Client</th>
              <th>Date</th>
              <th>Montant</th>
              <th>Statut</th>
            </tr>
          </thead>
          <tbody>
            {commandes.map((cmd) => (
              <tr key={cmd.id}>
                <td><strong>{cmd.numero}</strong></td>
                <td>{cmd.client}</td>
                <td>{formatDate(cmd.dateCommande)}</td>
                <td>{formatMontant(cmd.montant)}</td>
                <td>
                  <span className={`statut ${getStatutClass(cmd.statut)}`}>
                    {cmd.statut.replace('_', ' ')}
                  </span>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Accueil;
