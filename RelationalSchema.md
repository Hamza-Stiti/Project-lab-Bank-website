User(Membership_ID, name, emnail, password, DOB) key: Membership_ID

Currency(name, Membership_ID, IBAN) Key: (Name, Membership_ID), IBAN: unique, Membership_ID : foreign key from User

Transaction(SenderMembership_ID, Recipient_membershipID, currencyName, transfer_number) key: transfer_number
