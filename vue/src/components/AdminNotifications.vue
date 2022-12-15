<template>
  <section>
    <div class="admin-home-link">
      <router-link class="has-text-white is-size-5" :to="{ name: 'admin' }">Back to Admin Home</router-link>
    </div>
    <div class="profile-card is-size-3" v-if="!hasRequestsPending">No Requests Currently Pending</div>
    <div class="field" id="notif-housing">
      <table>
        <thead> 
          <tr>
            <th class="has-text-white">
                Select
              <!-- <label for="selectAllBox">
                    Select
                  <input type="checkbox" name="" id="selectAllBox" />
                </label> -->
            </th>
            <th class="has-text-white">Username</th>
            <th class="has-text-white">Role</th>
            <th class="has-text-white">Requested Admin</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in this.$store.state.allUsers" v-bind:key="user.id">
            <td>
              <input
                type="checkbox"
                v-bind:id="user.id"
                v-bind:value="user"
                v-model="selectedUsers"
              />
            </td>
            <td>{{ user.username }}</td>
            <td>{{ user.authorities[0].name.substring(5) }}</td>
            <td class="is-uppercase">{{ user.madeAdminRequest }}</td>
          </tr>
        </tbody>
      </table>
      <button @click.prevent="approveRequest()">Give Admin Access</button>
      <button @click.prevent="rejectRequest()">Revoke Admin Access</button>
    </div>
  </section>
</template>

<script>
import authService from "../services/AuthService.js";

export default {
  data() {
    return {
      selectedUsers: [],
    };
  },
  // destroyed() {
  //   //update db with unadded responses
  //     accountService.updateAccount(this.$store.state.account.accountId, this.$store.state.account);
  //     this.likeCount = 0;
  //     this.dislikeCount = 0;
  //   //update front-end movie arrays
  // },
  
  methods: {
    approveRequest() {
      for (let i = 0; i < this.$store.state.allUsers.length; i++) {
        for (let j = 0; j < this.selectedUsers.length; j++) {
          if (this.$store.state.allUsers[i] === this.selectedUsers[j]) {
            this.$store.state.allUsers[i].authorities[0].name = "ROLE_ADMIN";
            this.$store.state.allUsers[i].madeAdminRequest = false;
            authService.updateUser(
              this.$store.state.allUsers[i].id,
              this.$store.state.allUsers[i]
            );
          }
        }
      }
      this.selectedUsers = [];
    },
    rejectRequest() {
      for (let i = 0; i < this.$store.state.allUsers.length; i++) {
        for (let j = 0; j < this.selectedUsers.length; j++) {
          if (this.$store.state.allUsers[i] === this.selectedUsers[j]) {
            this.$store.state.allUsers[i].authorities[0].name = "ROLE_USER";
            this.$store.state.allUsers[i].madeAdminRequest = false;
            authService.updateUser(
              this.$store.state.allUsers[i].id,
              this.$store.state.allUsers[i]
            );
          }
        }
      }
      this.selectedUsers = [];
    },
  },
  computed: {
    usersRequesting() {
      return this.$store.state.allUsers.filter((user) => {
        return user.madeAdminRequest === true;
      });
    },
    hasRequestsPending() {
      return this.usersRequesting.length > 0;
    }
  },
};
</script>

<style>
.button {
  color: #fff;
  background: #573b8a;
}

.admin-home-link {
  border: 1px transparent;
  border-radius: 10px;
  background-color: hsl(0 0% 0% / 0.8);  
  color: #ffffff;
  margin: 25px 0px;
  display: grid;
  overflow: hidden;
}

#notif-housing {
  border: 1px transparent;
  border-radius: 10px;
  background-color: hsl(0 0% 0% / 0.8);  
  color: #ffffff;
  margin: 25px 0px;
  text-align: center;
  display: flex;
  flex-direction: column;
  
}

table, th, td {
  border: 1px solid #444;
}

</style>