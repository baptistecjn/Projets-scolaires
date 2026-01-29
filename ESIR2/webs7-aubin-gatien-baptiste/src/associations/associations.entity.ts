import { Minute } from "../minutes/minute.entity"; 
import { Column, Entity, PrimaryGeneratedColumn, OneToMany } from "typeorm";
import { Role } from "../roles/role.entity";

@Entity()
export class Associations {
    @PrimaryGeneratedColumn()
    id: number;

    @OneToMany(() => Role, (role) => role.association)
    roles: Role[];

    @Column()
    name: string;

    @OneToMany(() => Minute, minute => minute.association)
    minutes: Minute[];


}
