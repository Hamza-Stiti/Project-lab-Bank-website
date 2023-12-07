User(Membership_ID, name, email, password, DOB) key: Membership_ID

Currency(name, Membership_ID, IBAN) Key: (Name, Membership_ID), IBAN: unique, Membership_ID : foreign key from User

Transaction(Sender_IBAN, Recipient_IBAN, currencyName, transfer_number) key: transfer_number
