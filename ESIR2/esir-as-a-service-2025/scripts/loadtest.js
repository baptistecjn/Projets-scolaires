import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  vus: 10000,
  duration: '1m',
  insecureSkipTLSVerify: true,
};

export default function () {
  http.get('https://baptiste.istic.univ-rennes1.fr/gitea/');
  sleep(1);
}
