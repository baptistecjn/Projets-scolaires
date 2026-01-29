import { Column, Entity, PrimaryGeneratedColumn, ManyToMany } from "typeorm";
import { Minute } from "../minutes/minute.entity";
import { OneToMany } from "typeorm";
import { Role } from "../roles/role.entity";

@Entity()
export class User {
    @PrimaryGeneratedColumn()
    id: number;

    @Column({ unique: true })
    username: string;

    @Column()
    lastname: string;

    @Column()
    firstname: string;
    
    @Column({nullable:true})
    public age: number;
    
    @Column()
    password: string;
    
    @ManyToMany(() => Minute, minute => minute.voters)
    minutesVoted: Minute[];

    @OneToMany(() => Role, (role) => role.user)
    roles: Role[];


    constructor(id: number, username: string, lastname: string, firstname: string, age: number, password: string) {
        this.id = id;
        this.username = username;
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.password = password;
    }
}
