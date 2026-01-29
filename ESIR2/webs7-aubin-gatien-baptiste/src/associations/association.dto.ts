import { Member } from './association.member';

export class AssociationsDTO {
    id: number;
    name: string;
    members: Member[];
}