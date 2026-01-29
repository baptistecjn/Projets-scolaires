import { Entity, Column, PrimaryColumn, ManyToOne, JoinColumn } from "typeorm";
import { User } from "../users/user.entity";
import { Associations } from "../associations/associations.entity";

@Entity()
export class Role {

    @PrimaryColumn()
    idUser: number;

    @PrimaryColumn()
    idAssociation: number;

    @Column()
    name: string;

    @ManyToOne(() => User, { onDelete: "CASCADE" })
    @JoinColumn({ name: "idUser" })
    user: User;

    @ManyToOne(() => Associations, { onDelete: "CASCADE" })
    @JoinColumn({ name: "idAssociation" })
    association: Associations;
}
